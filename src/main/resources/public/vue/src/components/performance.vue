<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理系统</span><span>公会管理</span><span>绩效考核</span></div>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg"><el-form-item label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="主播ID：">
                    <el-input v-model="id" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getGuildContribution">搜索</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column prop="uid" label="主播ID"></el-table-column>
                        <el-table-column prop="date" label="日期"></el-table-column>
                        <el-table-column prop="total" label="产生收益"></el-table-column>
                        <el-table-column prop="performance" label="绩效指标"></el-table-column>
                        <el-table-column prop="reached" label="状态">
                            <span :slot-scope="row">
                                <!-- {{ row['reached'] == 0 ? '不达标' : '达标' }} -->
                            </span>
                        </el-table-column>
                        <el-table-column prop="" label="操作">
                        <template slot-scope="scope">
                            <el-button v-if="scope.row.reached == 0" @click="handleClick(scope.row)" type="text">
                                达标
                            </el-button>
                        </template>
                        </el-table-column>
                    </el-table>
                    <el-row>
                        <el-col :span="12" class="pagePosition pagination">共{{total}} 条记录 第 {{pageNum}} / {{pages}} 页</el-col>
                        <el-col :span="12">
                            <el-pagination
                                @current-change="handleCurrentChange"
                                :page-size="pageSize"
                                layout="prev, pager, next, jumper"
                                :total="total" class="pagination">
                            </el-pagination>
                        </el-col>
                    </el-row>
                </div>
           </el-form>
       </div>
    </div>
</template>
<script>
import moment from 'moment'
export default {
    data() {
        return {
            labelPosition: 'right',
            liveData: null,
            from: "",
            to: "",
            gm: "",
            page: "",
            time: "",
            id: "",
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            selectOption: [{
                value: 'down',
                label: '无'
                }, {
                value: 'up',
                label: '有'
            }],
            fakeData: [
            {
                "uid": 1003,
                "nickname": "king",
                "date": "2020-08-20",
                "total": 667.5000,
                "performance": 500,
                "reached": 1
            },
            {
                "uid": 1001,
                "nickname": "admin",
                "date": "2020-08-19",
                "total": 50.0000,
                "performance": 300,
                "reached": 0
            },
            {
                "uid": 1001,
                "nickname": "admin",
                "date": "2020-08-21",
                "total": 0.0000,
                "performance": 300,
                "reached": 0
            }]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            //  change url to id but now we don't have data
            // use api document's fake data 
            this.liveData = this.fakeData
            this.$axios.get('gm/rpt/perf/assessment', {
                params: {
                    from : this.from,
                    uid: this.id,
                    page: this.page
                }
            })
            .then(function (response) {
                this.liveData = response.data.data.data
                this.total= response.data.data.total
                this.pageNum= response.data.data.pageNum
                this.pages= response.data.data.pages
                this.pageSize= response.data.data.pageSize
            }.bind(this))
            .catch(function (error) {
                console.log('请求失败的返回值');
            });
        },
        resetForm(formName) {
            this.time = ""
            this.from = ''
            this.to = ''
            this.id =''
        },
        submitForm() {
            this.getGuildContribution()
        },
        handleCurrentChange(val) {
            // console.log(e)
            this.page = val
            this.getGuildContribution()
        },
        handleChange() {
            this.from = moment(this.time[0].toString()).format('yyyyMMDD')
            this.to =moment(this.time[1].toString()).format('yyyyMMDD')
        },
        handleClick(data) {
            // direct to streamer
            this.$axios.put('gm/rpt/perf/assessment', {
                params: {
                    uid : data.uid,
                    date: data.date
                }
            })
            this.$confirm('你确定要达标这行内容吗？', '达标修改', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
            .then(function (response) {
                if(response.data.status == 1) {
                    console.log("达标成功")
                    this.$message({
                        type: 'success',
                        message: '达标成功!'
                    });
                    this.getGuildContribution()
                }
            }.bind(this))
            .catch(function (error) {
                this.$message({
                    type: 'info',
                    message: '已取消达标'
                }); 
                console.log('请求失败的返回值');
            });
        }
    }
}
</script>