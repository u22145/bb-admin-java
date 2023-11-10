<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理</span><span>公会列表</span></div>
       <el-button type="warning" @click="directCreate" icon="el-icon-school" class="backTo">新建公會</el-button>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg">
                    <el-form-item label="公会ID：">
                        <el-input v-model="guildId" placeholder="请输入"></el-input>
                    </el-form-item>
                    <el-form-item label="会长D：">
                        <el-input v-model="masterId" placeholder="请输入"></el-input>
                    </el-form-item>
                    <el-form-item label="状态：">
                        <el-select v-model="status" placeholder="全部">
                            <el-option
                            v-for="item in selectOption"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getGuildContribution" icon="el-icon-search">搜索</el-button>
                        <el-button @click="resetForm">重置</el-button>
                        
                    </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table stripe :data="guildData" style="width: 100%">
                        <el-table-column prop="createdAt" label="新建时间" width="180">
                            
                        </el-table-column>
                        <el-table-column prop="name" label="公会名称" width="200"></el-table-column>
                        <el-table-column prop="id" label="公会ID" width="200"></el-table-column>
                        <el-table-column prop="masterId" label="会长ID" width="250"></el-table-column>
                        <el-table-column  label="状态">
                            <template slot-scope="scope">
                                <div :class="[scope.row.status == 1 ? '' : 'error-red']">{{scope.row.status == 1 ? "正常" : "已解散"}}</div> 
                            </template>
                        </el-table-column>
                        <el-table-column prop="" label="操作">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text">编辑</el-button>
                            <el-button type="text" @click="editDelete" class="deleteBT">删除</el-button>
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
            guildData: null,
            guildId: "",
            masterId: "",
            from: "",
            to: "",
            guild: "",
            gm: "",
            page: "",
            sort : "",
            time: "",
            status: "",
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            selectOption: [{
                value: 1,
                label: '正常'
                }, {
                value: 0,
                label: '已解散'
            }],
            fakeData: [
            {
                "id": 1,
                "masterId": 1016,
                "name": "soth的公會",
                "giftCommission": 0.4000,
                "lotteryCommission": 0.4000,
                "privateStreamCommission": 0.4000,
                "streamTicketCommission": 0.4000,
                "ugcCommission": 0.4000,
                "datingCommission": 0.4000,
                "messageCommission": 0.4000,
                "status": 1,
                "createdAt": "2020-08-23T11:10:45.000+00:00",
                "updatedAt": null
            },
            {
                "id": 2,
                "masterId": 1019,
                "name": "秋天的公會",
                "giftCommission": 0.3000,
                "lotteryCommission": 0.3000,
                "privateStreamCommission": 0.3000,
                "streamTicketCommission": 0.3000,
                "ugcCommission": 0.3000,
                "datingCommission": 0.3000,
                "messageCommission": 0.5000,
                "status": 1,
                "createdAt": "2020-08-23T11:10:45.000+00:00",
                "updatedAt": null
            },
            {
                "id": 3,
                "masterId": 1064,
                "name": "哈尼baby的公會",
                "giftCommission": 0.4500,
                "lotteryCommission": 0.3500,
                "privateStreamCommission": 0.3500,
                "streamTicketCommission": 0.4500,
                "ugcCommission": 0.3500,
                "datingCommission": 0.5500,
                "messageCommission": 0.5000,
                "status": 1,
                "createdAt": "2020-08-23T11:10:45.000+00:00",
                "updatedAt": null
            }]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            this.guildData =this.fakeData
            this.$axios.get('guild', {
                params: {
                    status : this.status ,
                    page: this.page,
                    guild : this.guildId,
                    gm : this.masterId,
                }
            })
            .then(function (response) {
                this.guildData = response.data.data.data
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
            this.guildId = ""
            this.masterId = ''
            this.status = ''
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
        handleClick(data){
          this.$router.push({ path: '/editGuild', query: {id: data.masterId, name: data.name,guildId:data.id  }})
        },
        directCreate() {
            this.$router.push('/createGuild')
        },
        editDelete(){
           this.$confirm('删除后该公会抽成将无法正常计算，操作不可逆，你还要继续吗？', '删除公会', {
                confirmButtonText: '确定删除',
                cancelButtonText: '取消删除',
                type: 'warning'
            }).then(() => {
                this.$message({
                type: 'success',
                message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消删除'
                });          
            });
        }
    }
}
</script>