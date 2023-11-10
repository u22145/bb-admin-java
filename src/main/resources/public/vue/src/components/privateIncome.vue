<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span><span>私信收益</span></div>
       <el-button type="primary" @click="backTo" class="backTo">返回</el-button>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg "><el-form-item label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="uid" placeholder="请输入"></el-input>
                </el-form-item>
                <!-- <el-form-item label="有无公会：">
                    <el-select v-model="sort" placeholder="请选择">
                        <el-option
                        v-for="item in selectOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item>
                    <el-button type="primary" @click="getGuildContribution">搜索</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table show-summary :summary-method="getSummaries" stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column prop="uid" label="主播ID"></el-table-column>
                        <el-table-column prop="pmExp" label="*私信收益"></el-table-column>
                        <el-table-column prop="pmCnt" label="*条数"></el-table-column>
                        <el-table-column prop="senders" label="人数"></el-table-column>
                        <el-table-column prop="" label="操作">
                            <el-button @click="dialogTableVisible = true" type="text">详情</el-button>
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
                <el-dialog title="私信收益详情" :visible.sync="dialogTableVisible">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="pmExp" label="*时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*金额"></el-table-column>
                    </el-table>
                    <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                    </el-pagination>
                </el-dialog>
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
            sort : "",
            time: "",
            uid: "",
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
            dialogTableVisible: false,
            dialogFormVisible: false,
            form: {
            name: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
            },
            formLabelWidth: '120px',
            fakeData: [
                {
                    "uid": 1003,
                    "nickname": "king",
                    "pmExp": 1.0000,
                    "pmCnt": 2,
                    "senders": 2
                },
                {
                    "uid": 1006,
                    "nickname": "后裔0",
                    "pmExp": 0.5000,
                    "pmCnt": 1,
                    "senders": 1
            }],
            popupData: [
                {
                    "uid": 1003,
                    "nickname": "king",
                    "pmExp": 1.0000,
                    "pmCnt": 2,
                    "senders": 2
                },
                {
                    "uid": 1006,
                    "nickname": "后裔0",
                    "pmExp": 0.5000,
                    "pmCnt": 1,
                    "senders": 1
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
            // this.liveData =this.fakeData
            this.$axios.get('guild/rpt/pm', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild  : this.guild ,
                    sort : this.sort
                }
            })
            .then(function (response) {
                this.liveData = response.data.data.datathis.total= response.data.data.total
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
            this.sort= ''
            this.uid= ''
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
        },
        backTo(data) {
            this.$router.push('/dataDetail')
        },
        dialogTableVisible(){
            // this.liveData =this.popupData
            // this.$axios.get('guild/rpt/pm/detail', {
            //     params: {
            //         from : this.from,
            //         to: this.to,
            //         page: this.page,
            //         uid  : this.uid ,
            //     }
            // })
            // .then(function (response) {
            //     this.liveData = response.data.data.data
            // }.bind(this))
            // .catch(function (error) {
            //     console.log('请求失败的返回值');
            // });
        },
        getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
            var thHeader=document.getElementsByTagName("th")
            var tdText=thHeader[index].innerText;
            if (index <=0 || index>4) {
                sums[index] = '';
                return;
            }else if(index === 1){
                sums[index] = '总计';
                return;
            };
            const values = data.map(item => Number(item[column.property]));
            if (!values.every(value => isNaN(value))) {
                sums[index] = values.reduce((prev, curr) => {
                const value = Number(curr);
                if (!isNaN(value)) {
                    return prev + curr;
                } else {
                    return prev;
                }
                }, 0);
                if (tdText.indexOf('/人')>=0){
                    sums[index] += ' /人'; 
                }
            } else {
                
                    sums[index] = 'NaN'; 
            }
        });

        return sums;
        }
    }
}
</script>