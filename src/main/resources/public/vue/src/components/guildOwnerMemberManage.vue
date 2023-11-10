<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理系统</span><span>公会管理</span><span>成员列表</span></div>
       <el-button type="warning" @click="goTo" icon="el-icon-user-solid" class="backTo">邀請成員</el-button>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg"><el-form-item label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="主播ID：">
                    <el-input v-model="uid" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getGuildContribution">搜索</el-button>
                    <el-button @click="resetForm">重置</el-button>
                    
                </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table stripe :data="guildData" style="width: 100%">
                        <el-table-column width="150" prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column width="135" prop="uid" label="主播ID"></el-table-column>
                        <el-table-column width="135" prop="giftCommission" label="＊礼物抽成"></el-table-column>
                        <el-table-column width="135" prop="lotteryCommission" label="＊彩票盈利抽成"></el-table-column>
                        <el-table-column width="135" prop="privateStreamCommission" label="＊1V1视频抽成"></el-table-column>
                        <el-table-column width="135" prop="streamTicketCommission" label="＊直播间门票抽成" ></el-table-column>
                        <el-table-column width="135" prop="datingCommission" label="＊约会电台抽成"></el-table-column>
                        <el-table-column width="135" prop="ugcCommission" label="＊UGC抽成"></el-table-column>
                        <el-table-column width="135" prop="messageCommission" label="＊私信抽成"></el-table-column>
                        <el-table-column width="135" prop="performance" label="＊绩效指标"></el-table-column>
                        <el-table-column width="135" prop="perfReached" label="＊考核记录">
                            <template slot-scope="scope">
                                <div>{{scope.row.perfReached == 1 ? "达标" : "不达标"}}</div>
                                <!-- <router-link v-bind:to="'/streamHome'">{{scope.row.liveDuration}}</router-link> -->
                            </template>
                        </el-table-column>
                        <el-table-column width="135" label="＊状态">
                            <template slot-scope="scope">
                                <div>{{checkStatus(scope.row.status)}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="" label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button @click="handleEdit(scope.row)" type="text">編輯</el-button>
                            <el-button @click="editDelete" type="text" class="deleteBT">踢出公會</el-button>
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
            from: "",
            to: "",
            guild: "",
            gm: "",
            page: "",
            sort : "",
            time: "",
            uid: "",
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            fakeData: [
            {
                "uid": 1001,
                "nickname": "admin",
                "tierId": 1,
                "giftCommission": 0.5000,
                "lotteryCommission": 0.5000,
                "privateStreamCommission": 0.5000,
                "streamTicketCommission": 0.5000,
                "ugcCommission": 0.5000,
                "datingCommission": 0.5000,
                "messageCommission": 0.5000,
                "performance": 300,
                "perfReached": 0,
                "perfMissed": 4,
                "status": 0
            },
            {
                "uid": 1003,
                "nickname": "king",
                "tierId": 2,
                "giftCommission": 0.5000,
                "lotteryCommission": 0.5000,
                "privateStreamCommission": 0.5000,
                "streamTicketCommission": 0.5000,
                "ugcCommission": 0.5000,
                "datingCommission": 0.5000,
                "messageCommission": 0.5000,
                "performance": 500,
                "perfReached": 1,
                "perfMissed": 3,
                "status": 0
            }
        ]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            this.guildData = this.fakeData
            this.$axios.get('gm/anchor/', {
                params: {
                    from : this.from,
                    to: this.to,
                    uid: this.uid,
                    page: this.page
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
            this.time = ""
            this.from = ''
            this.to = ''
            this.uid = ''
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
        handleEdit(data){
            console.log(data)
          this.$router.push({ path: '/guildOwnerEdit', query: {id: data.uid }})
        },
        goTo(data) {
            this.$router.push('/guildOwnerInvite')
        },
        editDelete(){
            this.$confirm('踢出后该公会抽成将无法正常计算，操作不可逆，你还要继续吗？', '踢出公会', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                type: 'success',
                message: '踢出成功!'
                });
            }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消踢出'
                });          
            });
          this.$router.push({ path: '/guildOwnerEdit', query: {id: data.uid }})
        },
        checkStatus(status) {
            switch(status) {
            case 0:
                return "离线"
                break;
            case 1:
                return "在线"
                break;
            case 2:
                return "正在直播"
            default:
                // code block
            }
        }
    }
}
</script>