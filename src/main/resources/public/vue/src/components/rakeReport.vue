<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>抽成结算报表</span></div>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg"><el-form-item class="mb20" label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="guildId" placeholder="请输入"></el-input>
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
                    <el-table show-summary :summary-method="getSummaries" stripe :data="guildData" style="width: 100%">
                        <el-table-column width="130" prop="guildName" label="公会名称" ></el-table-column>
                        <el-table-column width="130" prop="guildId" label="公会ID"></el-table-column>
                        <el-table-column width="130" prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column width="130" prop="uid" label="*主播ID"></el-table-column>
                        <el-table-column width="130" prop="giftExp" label="*礼物打赏"></el-table-column>
                        <el-table-column width="130" prop="ticketExp" label="*直播间门票"></el-table-column>
                        <el-table-column width="130" prop="psExp" label="*1V1视频计时"></el-table-column>
                        <el-table-column width="130" prop="lottoExp" label="*彩票盈利"></el-table-column>
                        <el-table-column width="130" prop="ugcExp" label="*UGC收益" ></el-table-column>
                        <el-table-column width="130" prop="ugcExp" label="*约会电台收益" ></el-table-column>
                        <el-table-column width="130" prop="pmExp" label="*私信收益"></el-table-column>
                        <el-table-column width="130" prop="total" label="*总收益"></el-table-column>
                        <el-table-column width="130" prop="commission" label="*公会抽成"></el-table-column>
                        <el-table-column width="130" prop="anchorCommission" label="主播抽成"></el-table-column>
                        <el-table-column width="130" prop="totalCommission" label="总抽成"></el-table-column>
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
            guildId: "",
            uid: "",
            gm: "",
            page: "",
            sort : "",
            time: "",
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            fakeData: [
            {
                "guildId": 1,
                "guildName": "soth的公會",
                "uid": 1001,
                "nickname": "admin",
                "giftExp": 0.0000,
                "ticketExp": 0.0000,
                "psExp": 50.0000,
                "lottoExp": 0.0000,
                "ugcExp": 0.0000,
                "datingExp": 0.0000,
                "pmExp": 0.0000,
                "total": 50.0000,
                "guildCommission": 10.0000,
                "anchorCommission": 10.0000,
                "totalCommission": 20.0000
            },
            {
                "guildId": 1,
                "guildName": "soth的公會",
                "uid": 1003,
                "nickname": "king",
                "giftExp": 306.0000,
                "ticketExp": 0.0000,
                "psExp": 0.0000,
                "lottoExp": 361.5000,
                "ugcExp": 0.0000,
                "datingExp": 0.0000,
                "pmExp": 1.0000,
                "total": 668.5000,
                "guildCommission": 133.7000,
                "anchorCommission": 133.7000,
                "totalCommission": 267.4000
            },
            {
                "guildId": 2,
                "guildName": "秋天的公會",
                "uid": 1004,
                "nickname": "Housing 1",
                "giftExp": 2560.0000,
                "ticketExp": 0.0000,
                "psExp": 0.0000,
                "lottoExp": 288.0000,
                "ugcExp": 0.0000,
                "datingExp": 0.0000,
                "pmExp": 0.0000,
                "total": 2848.0000,
                "guildCommission": 427.2000,
                "anchorCommission": 427.2000,
                "totalCommission": 854.4000
            },
            {
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "uid": 1005,
                "nickname": "后裔",
                "giftExp": 75.0000,
                "ticketExp": 0.0000,
                "psExp": 28.0000,
                "lottoExp": 242.0000,
                "ugcExp": 0.0000,
                "datingExp": 0.0000,
                "pmExp": 0.0000,
                "total": 345.0000,
                "guildCommission": 76.2250,
                "anchorCommission": 76.2250,
                "totalCommission": 152.4500
            },
            {
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "uid": 1006,
                "nickname": "后裔0",
                "giftExp": 0.0000,
                "ticketExp": 0.0000,
                "psExp": 0.0000,
                "lottoExp": 0.0000,
                "ugcExp": 0.0000,
                "datingExp": 0.0000,
                "pmExp": 0.5000,
                "total": 0.5000,
                "guildCommission": 0.1250,
                "anchorCommission": 0.1250,
                "totalCommission": 0.2500
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
            this.$axios.get('guild/rpt/perf/commission', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild: this.guildId,
                    uid: this.uid
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
            this.guildId = ""
            this.uid = ""
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
            if (index <=2) {
                sums[index] = '';
                return;
            }
            if (index === 3) {
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
               
                    sums[index] = 'NaN'; 
                
            }
            });
            return sums;
        }
    }
}
</script>