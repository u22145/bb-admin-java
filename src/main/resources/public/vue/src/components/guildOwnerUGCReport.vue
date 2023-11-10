<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理系统</span><span>报表管理</span><span>UGC报表</span></div>
       <!-- <el-button type="primary" @click="backTo" class="backTo">返回</el-button> -->
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg "><el-form-item label="时间：">
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
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column width="130" prop="fromDT" label="主播昵称" ></el-table-column>
                    <el-table-column width="130" prop="toDT" label="主播ID"></el-table-column>
                    <el-table-column width="130" prop="nickname" label="主播昵称"></el-table-column>
                    <el-table-column width="150" label="*社区视频收益/人">
                       <template slot-scope="scope">
                            <el-button @click="duration = true" type="text">{{scope.row.duration}}</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="150" label="*社区图片收益/人">
                       <template slot-scope="scope">
                            <el-button @click="streamExp = true" type="text">{{scope.row.streamExp}}</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="150" label="*社区小说收益/人">
                       <template slot-scope="scope">
                            <el-button @click="total = true" type="text">{{scope.row.total}}</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="150" label="*相册隐私收益/人">
                       <template slot-scope="scope">
                            <el-button @click="nickname = true" type="text">{{scope.row.nickname}}</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="150" label="*啪啪短视频收益/人">
                       <template slot-scope="scope">
                            <el-button @click="giftExp = true" type="text">{{scope.row.giftExp}}</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="130" prop="total" label="*总收益"></el-table-column>
                </el-table>
                <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                </el-pagination>
                </div>  
                <el-dialog title="社区视频收益详情" :visible.sync="duration">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="startedAt" label="*打赏时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物名称"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物价格"></el-table-column>
                    </el-table>
                    <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                    </el-pagination>
                </el-dialog>
                <el-dialog title="社区图片收益" :visible.sync="streamExp">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="startedAt" label="*打赏时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物名称"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物价格"></el-table-column>
                    </el-table>
                    <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                    </el-pagination>
                </el-dialog>
                <el-dialog title="社区小说收益" :visible.sync="total">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="startedAt" label="*打赏时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物名称"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物价格"></el-table-column>
                    </el-table>
                    <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                    </el-pagination>
                </el-dialog>
                <el-dialog title="相册隐私收益" :visible.sync="nickname">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="startedAt" label="*打赏时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物名称"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物价格"></el-table-column>
                    </el-table>
                    <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                    </el-pagination>
                </el-dialog>
                <el-dialog title="啪啪短视频收益/人" :visible.sync="giftExp">
                    <el-table stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="startedAt" label="*打赏时间" ></el-table-column>
                        <el-table-column prop="uid" label="*用户ID"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物名称"></el-table-column>
                        <el-table-column prop="nickname" label="*礼物价格"></el-table-column>
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
            fakeData: [
            {
                "uid": 1003,
                "nickname": "king",
                "durationTotal": "01:34:46",
                "ticketExp": 361.5000,
                "ticketBuyers": 241,
                "giftExp": 306.0000,
                "giftBuyers": 7,
                "lotteryExp": 0,
                "viewers": 241,
                "avgViewers": 120.5000,
                "total": 667.5000
            },
                        {
                "uid": 1003,
                "nickname": "king",
                "durationTotal": "01:34:46",
                "ticketExp": 361.5000,
                "ticketBuyers": 241,
                "giftExp": 306.0000,
                "giftBuyers": 7,
                "lotteryExp": 0,
                "viewers": 241,
                "avgViewers": 120.5000,
                "total": 667.5000
            },
            {
                "uid": 1003,
                "nickname": "king",
                "durationTotal": "01:34:46",
                "ticketExp": 361.5000,
                "ticketBuyers": 241,
                "giftExp": 306.0000,
                "giftBuyers": 7,
                "lotteryExp": 0,
                "viewers": 241,
                "avgViewers": 120.5000,
                "total": 667.5000
            }
            ],
            duration:false,
            giftExp:false,
            streamExp:false,
            total:false,
            nickname:false,
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
            formLabelWidth: '50%'
        }
    },
    beforeMount() {
        this.uid = this.$route.query.id  
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            this.$axios.get('gm/rpt/ps/detail', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    uid: this.uid
                }
            })
            .then(function (response) {
                this.liveData = response.data.data.data
                // this.liveData = this.fakeData
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
        handleClick(data) {
            // direct to streamer
        },
        backTo(data) {
            this.$router.push('/guildOwnerOneOnOneReport')
        }
    }
}
</script>