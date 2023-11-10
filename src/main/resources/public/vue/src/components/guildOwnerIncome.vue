<template>
    <div class="guildOwnerIncome">
        <div class="Breadcrumbs"><span>公会管理系统</span><span>报表管理</span><span>收益报表</span></div>
        <el-row>
            <el-col :span="9" :push="14">
                <el-button type="primary" plain>今日</el-button>
                <el-button>昨日</el-button>
                <el-button>本周</el-button>
                <el-button>本月</el-button>
                <el-button>上月</el-button>
            </el-col>
        </el-row>
        <div class="screen whiteBg ">
            <el-row class="borderStyle information" type="flex" justify="space-between" style="margin-bottom: 20px; padding-bottom: 0px;">
                <div class="FG userIcon"><strong><i class="el-icon-user-solid"></i>124,345</strong></div>
                <div class="FG"><span>主播在线人数</span><strong>124,345</strong></div>
                <div class="FG"><span>产生收益</span><strong>124,345</strong></div>
                <div class="FG"><span>主播抽成</span><strong>124,345</strong></div>
                <div class="FG"><span>公会抽成</span><strong>124,345</strong></div>
            </el-row>
            <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <el-form-item class="mb20" label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="guildId" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="会长D：">
                    <el-input v-model="masterId" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getGuildContribution">搜索</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
           </el-form>
        </div>
            <div class="information whiteBg">
           <el-table show-summary :summary-method="getSummaries" stripe :data="guildData" style="width: 100%">
                    <!-- <el-table-column width="130" prop="guildName" label="公会名称" ></el-table-column>
                    <el-table-column width="130" prop="guildId" label="公会ID"></el-table-column> -->
                    <el-table-column width="130" prop="nickname" label="主播昵称" ></el-table-column>
                    <el-table-column width="130" prop="uid" label="主播ID"></el-table-column>
                    <el-table-column width="130" prop="giftExp" label="礼物打赏"></el-table-column>
                    <el-table-column width="130" prop="ticketExp" label="直播间门票"></el-table-column>
                    <el-table-column width="130" prop="psExp" label="1V1视频计时"></el-table-column>
                    <el-table-column width="130" prop="lottoExp" label="彩票盈利"></el-table-column>
                    <el-table-column width="130" prop="ugcExp" label="UGC收益" ></el-table-column>
                    <el-table-column width="130" prop="pmExp" label="私信收益"></el-table-column>
                    <el-table-column width="130" prop="total" label="总收益"></el-table-column>
                    <el-table-column width="130" prop="commission" label="公会抽成"></el-table-column>
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
            masterId: "",
            gm: "",
            page: "",
            sort : "",
            time: "",
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            selectOption: [{
                value: 'down',
                label: '总收益降序显示'
                }, {
                value: 'up',
                label: '总收益降序显示'
            }]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            this.$axios.get('guild/rpt/perf/commission', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild: this.guildId,
                    uid: this.masterId
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
            this.masterId = ""
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
            this.from = moment(this.time[0].toString()).format('yyyyMMdd')
            this.to =moment(this.time[1].toString()).format('yyyyMMdd')
        },
        getSummaries(param) {
            const { columns, data } = param;
            const sums = [];

            // var livePlainTime = "00:00:00",liveDurationAll;
            // data.forEach((data ,index) =>{
            //     // 時間換算
            //     let liveDuration=param.data[index].duration
            //     var liHour = liveDuration.split(':')[0];
            //     var liMinutes = liveDuration.split(':')[1];
            //     var liSeconds = liveDuration.split(':')[2];

            //     if(index == 0) {
            //         liveDurationAll = moment(livePlainTime, "HH:mm:ss")
            //                         .add(liSeconds, 'seconds')
            //                         .add(liMinutes, 'minutes')
            //                         .add(liHour, 'hour')
            //                         .format('LTS');              
            //     }
            //     else {
            //         liveDurationAll = moment(liveDurationAll, "HH:mm:ss")
            //                         .add(liSeconds, 'seconds')
            //                         .add(liMinutes, 'minutes')
            //                         .add(liHour, 'hour')
            //                         .format('LTS');
            //     }
                
            // })
            // console.log(liveDurationAll)
            columns.forEach((column, index) => {
            var thHeader=document.getElementsByTagName("th")
            var tdText=thHeader[index].innerText;
            // console.log(tdText)
            if (index <=0) {
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
               
                    if (tdText.indexOf('1V1视频时长')>=0){
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