<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span><span>直播间详情</span><span>详情</span></div>
       <el-button type="primary" @click="backTo" class="backTo">返回</el-button>
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
                    <el-table show-summary :summary-method="getSummaries" stripe :data="liveData" style="width: 100%">
                        <el-table-column width="135" prop="startedAt" label="开始时间" >
                            <!-- <template slot-scope="scope">
                                {{scope.row.moment(startedAt).format('yyyyMMdd hh:mm:ss',true)}}
                            </template>   -->
                        </el-table-column>
                        <el-table-column width="135" prop="closedAt" label="结束时间"></el-table-column>
                        <el-table-column width="135" prop="guildId" label="公会ID"></el-table-column>
                        <el-table-column width="135" prop="nickname" label="主播昵称"></el-table-column>
                        <el-table-column width="135" prop="uid" label="主播ID"></el-table-column>
                        <el-table-column width="135" prop="duration" label="直播时长"></el-table-column>
                        <el-table-column width="135" prop="ticketPrice" label="门票单价" ></el-table-column>
                        <el-table-column width="135" prop="ticketExp" label="门票收益/人">
                            <template slot-scope="scope">
                                <el-button @click="ticketExp = true" type="text">{{scope.row.ticketExp}}</el-button>
                            </template>   
                        </el-table-column>
                        <el-table-column width="135" prop="giftExp" label="礼物打赏/人">
                            <template slot-scope="scope">
                                <el-button @click="giftExp = true" type="text">{{scope.row.giftExp}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="135" prop="lottoExp" label="彩票盈利">
                            <template slot-scope="scope">
                                <el-button @click="lottoExp = true" type="text">{{scope.row.lottoExp}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="135" prop="viewers" label="进入直播间人次"></el-table-column>
                        <el-table-column width="135" prop="total" label="公屏条数/人"></el-table-column>
                        <el-table-column width="135" prop="total" label="总收益"></el-table-column>
                        <el-table-column width="135" prop="peakViewers" label="最高在线人数"></el-table-column>
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
           <el-dialog title="门票收益详情" :visible.sync="ticketExp">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*门票单价"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
            <el-dialog title="礼物打赏详情" :visible.sync="giftExp">
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
            <el-dialog title="彩票盈利详情" :visible.sync="lottoExp">
                <el-table stripe :data="liveData" style="width: 100%" class="profitStatus">
                    <el-table-column width="140" prop="startedAt" label="*投注时间" ></el-table-column>
                    <el-table-column width="120" prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*彩票名称"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*玩法群"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*玩法组"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*投注内容"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*投注金额"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*状态"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*中奖金额"></el-table-column>
                    <el-table-column width="120" prop="nickname" label="*盈利"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
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
            fakeData: [
            {
                "uid": 1005,
                "nickname": "后裔",
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "duration": "00:50:50",
                "ticketPrice": 2.0000,
                "ticketBuyers": 121,
                "ticketExp": 242.0000,
                "giftBuyers": 3,
                "giftExp": 75.0000,
                "lottoExp": 0.0000,
                "total": 317.0000,
                "viewers": 121,
                "peakViewers": 99,
                "startedAt": "2020-08-20 08:00",
                "closedAt": "2020-08-20 08:00"
            },
                        {
                "uid": 1005,
                "nickname": "后裔",
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "duration": "00:50:50",
                "ticketPrice": 2.0000,
                "ticketBuyers": 121,
                "ticketExp": 242.0000,
                "giftBuyers": 3,
                "giftExp": 75.0000,
                "lottoExp": 0.0000,
                "total": 317.0000,
                "viewers": 121,
                "peakViewers": 99,
                "startedAt": "2020-08-20 08:00",
                "closedAt": "2020-08-20 08:"
            },
            {
                "uid": 1005,
                "nickname": "后裔",
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "duration": "00:50:50",
                "ticketPrice": 2.0000,
                "ticketBuyers": 121,
                "ticketExp": 242.0000,
                "giftBuyers": 3,
                "giftExp": 75.0000,
                "lottoExp": 0.0000,
                "total": 317.0000,
                "viewers": 121,
                "peakViewers": 99,
                "startedAt": "2020-08-20 08:00",
                "closedAt": "2020-08-20 08:00"
            }],
            ticketExp:false,
            giftExp:false,
            lottoExp:false,
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
            formLabelWidth: '80%'
        }
    },
    beforeMount() {
        this.id = this.$route.query.id 
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            // cause now we don't have data to search so use fake data
            // this.liveData =this.fakeData
            this.$axios.get('guild/rpt/live/detail', {
                params: {
                    uid: this.id,
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    sort: this.sort
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
            this.uid = ''
            this.sort = ''
            
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
            this.$router.push('/streamHome')
        },
        getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        var livePlainTime = "00:00:00",liveDurationAll;
        data.forEach((data ,index) =>{
            // 時間換算
            let liveDuration=param.data[index].duration
            var liHour = liveDuration.split(':')[0];
            var liMinutes = liveDuration.split(':')[1];
            var liSeconds = liveDuration.split(':')[2];

            if(index == 0) {
                liveDurationAll = moment(livePlainTime, "HH:mm:ss")
                                .add(liSeconds, 'seconds')
                                .add(liMinutes, 'minutes')
                                .add(liHour, 'hour')
                                .format('LTS');         
            }
            else {
                liveDurationAll = moment(liveDurationAll, "HH:mm:ss")
                                .add(liSeconds, 'seconds')
                                .add(liMinutes, 'minutes')
                                .add(liHour, 'hour')
                                .format('LTS');
            }
            
        })
        console.log(liveDurationAll)
        columns.forEach((column, index) => {
            var thHeader=document.getElementsByTagName("th")
            var tdText=thHeader[index].innerText;
            if (index <=3 || index>12) {
                sums[index] = '';
                return;
            }else if(index === 4){
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
                if(tdText.indexOf('直播时长')>=0) {
                    sums[index] = liveDurationAll.replace(/[a-zA-Z]+/, ""); 
                    return;
                }else {
                    sums[index] = 'NaN'; 
                }
            }
        });

        return sums;
        }
    }
}
</script>
