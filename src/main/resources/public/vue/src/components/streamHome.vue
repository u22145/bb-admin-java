<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span><span>直播间详情</span></div>
       <el-button type="primary" @click="backTo" class="backTo">返回</el-button>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg "><el-form-item label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="guildId" placeholder="请输入"></el-input>
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
                    <el-table :summary-method="getSummaries" show-summary stripe :data="liveData" style="width: 100%">
                        <el-table-column width="135" prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column width="135" prop="uid" label="主播ID"></el-table-column>
                        <el-table-column width="135" prop="durationTotal" label="直播时长"></el-table-column>
                        <el-table-column width="135" prop="ticketExp" label="门票收益/人"></el-table-column>
                        <el-table-column width="135" prop="giftExp" label="礼物打赏/人"></el-table-column>
                        <el-table-column width="135" prop="lotteryExp" label="彩票盈利"></el-table-column>
                        <el-table-column width="135" prop="viewers" label="进入直播间人次" ></el-table-column>
                        <el-table-column width="135" prop="total" label="公屏条数/人"></el-table-column>
                        <el-table-column width="135" prop="total" label="总收益"></el-table-column>
                        <el-table-column width="135" prop="avgViewers" label="平均在线人数"></el-table-column>
                        <el-table-column width="135" prop="" label="操作">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text">详情</el-button>
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
            sort : "",
            time: "",
            guildId: "",
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
                "durationTotal": "01:34:46",
                "ticketExp": 361.5000,
                "ticketBuyers": 241,
                "giftExp": 306.0000,
                // "giftBuyers": 7,
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
                // "giftBuyers": 7,
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
                // "giftBuyers": 7,
                "lotteryExp": 0,
                "viewers": 241,
                "avgViewers": 120.5000,
                "total": 667.5000
            }
            ]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            // this.liveData = this.fakeData
            this.$axios.get('guild/rpt/live', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild: this.guildId,
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
            this.guildId = ''
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
        handleClick(data){
          this.$router.push({ path: '/streamDetail', query: {id: data.nickname, name: data.uid }})
        },
        backTo(data) {
            this.$router.push('/dataDetail')
        },
        getSummaries(param) {
            const { columns, data } = param;
            const sums = [];

            var livePlainTime = "00:00:00",liveDurationAll;
            data.forEach((data ,index) =>{
                // 時間換算
                let liveDuration=param.data[index].durationTotal
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
            // console.log(tdText)
            if (index <=0 || index>8) {
                sums[index] = '';
                return;
            }
            if (index === 1) {
                sums[index] = '总计';
                return;
            }
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
                if (tdText.indexOf('直播时长')>=0){
                    sums[index] = liveDurationAll.replace(/[a-zA-Z]+/, ""); 
                }else{
                    sums[index] = 'NaN'; 
                }
            }
            });
            return sums;
        }
    }
}
</script>