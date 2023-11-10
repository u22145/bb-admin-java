<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span></div>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg">
                <el-form-item label="时间：" style="margin-bottom: 20px;">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="guildId" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="会长D：">
                    <el-input v-model="masterId" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="有无公会：">
                    <el-select v-model="sort" placeholder="全部">
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
                    <!-- <el-button type="primary" @click="submitForm" >返回</el-button> -->
                </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table show-summary :summary-method="getSummaries" stripe :data="guildData" style="width: 100%">
                        <!-- <el-table-column prop="createdAt" label="新建时间" width="180"></el-table-column> -->
                        <el-table-column width="154" prop="guildName" label="公会名称"></el-table-column>
                        <el-table-column width="154" prop="guildId" label="公会ID"></el-table-column>
                        <el-table-column width="154" prop="gmId" label="会长ID"></el-table-column>
                        <el-table-column width="154" prop="liveDuration" label="直播时长"></el-table-column>
                        <!-- <el-table-column width="154" label="直播时长">
                            <template slot-scope="scope">
                                <router-link v-bind:to="'/streamHome'">{{scope.row.liveDuration}}</router-link>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="154" prop="ticketExp" label="门票收益/人">
                             <template slot-scope="scope">
                                {{scope.row.ticketExp}} / {{scope.row.guildName}}
                            </template>
                        </el-table-column>
                        <el-table-column width="154" prop="giftExp" label="礼物打赏/人"></el-table-column>
                        <!-- <el-table-column width="154" label="礼物打赏/人">
                            <template slot-scope="scope">
                                <div>{{giftExp(scope.row.giftExp)}}</div>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="150" prop="psDuration" label="1V1视频时长/收益"></el-table-column>
                        <!-- <el-table-column width="150" label="1V1视频时长/收益">
                            <template slot-scope="scope">
                                <router-link v-bind:to="'/oneOnoneHome'">{{scope.row.psExp}}</router-link>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="141" prop="lottoExp" label="彩票盈利"></el-table-column>
                        <el-table-column width="141" prop="ugcExp" label="UGC收益"></el-table-column>
                        <!-- <el-table-column width="141" label="UGC收益">
                            <template slot-scope="scope">
                                <router-link v-bind:to="'/ugcDetail'">{{scope.row.ugcExp}}</router-link>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="150" prop="ugcExp" label="约会电台收益/人"></el-table-column>
                        <!-- <el-table-column width="141" label="约会电台收益/人">
                            <template slot-scope="scope">
                                <router-link v-bind:to="'/dateIncome'">{{scope.row.ugcExp}}</router-link>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="141" prop="ugcExp" label="私信收益/条"></el-table-column>
                        <!-- <el-table-column width="141" label="私信收益/条">
                            <template slot-scope="scope">
                                <router-link v-bind:to="'/privateIncome'">{{scope.row.pmCnt}}</router-link>
                            </template>
                        </el-table-column> -->
                        <el-table-column width="141" prop="total" label="总收益"></el-table-column>

                        <el-table-column width="300" label="操作">
                            <router-link v-bind:to="'/streamHome'">直播间详情</router-link> | 
                            <router-link v-bind:to="'/oneOnoneHome'">1V1视频时长</router-link> | 
                            <router-link v-bind:to="'/ugcDetail'">UGC收益</router-link> | 
                            <router-link v-bind:to="'/dateIncome'">约会电台收益</router-link> | 
                            <router-link v-bind:to="'/privateIncome'">私信收益</router-link>
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
// import '@/utils/mixins'
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
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            fakeData:[
                {
                    "guildId": 2,
                    "guildName": "假資料",
                    "gmId": 1019,
                    "gmName": "秋天",
                    "liveDuration": "01:02:32",
                    "ticketExp": 288.0000,
                    "ticketBuyers": 288,
                    "giftExp": 2560.0000,
                    "giftBuyers": 10,
                    "psDuration": "03:12:10",
                    "psExp": 0.0000,
                    "lottoExp": 0,
                    "ugcExp": 0,
                    "pmExp": 0.0000,
                    "pmCnt": 0,
                    "total": 2848.0000
                },
                {
                    "guildId": 1,
                    "guildName": "2假資料",
                    "gmId": 1019,
                    "gmName": "秋天",
                    "liveDuration": "02:02:31",
                    "ticketExp": 288.0000,
                    "ticketBuyers": 288,
                    "giftExp": 2560.0000,
                    "giftBuyers": 10,
                    "psDuration": "02:05:02",
                    "psExp": 0.0000,
                    "lottoExp": 0,
                    "ugcExp": 0,
                    "pmExp": 0.0000,
                    "pmCnt": 0,
                    "total": 2848.0000
                },
                {
                    "guildId": 3,
                    "guildName": "3假資料",
                    "gmId": 1019,
                    "gmName": "323秋天",
                    "liveDuration": "05:00:32",
                    "ticketExp": 288.0000,
                    "ticketBuyers": 288,
                    "giftExp": 2560.0000,
                    "giftBuyers": 10,
                    "psDuration": "01:00:03",
                    "psExp": 0.0000,
                    "lottoExp": 0,
                    "ugcExp": 0,
                    "pmExp": 0.0000,
                    "pmCnt": 0,
                    "total": 2848.0000
                }
            ],
            selectOption: [{
                value: 'down',
                label: '有'
                }, {
                value: 'up',
                label: '沒有'
            }],
        }
    },
    mounted() {
        this.getGuildContribution()
        // this.getSummaries(param)
    },
    methods: {
        getGuildContribution() {
            this.guildData = this.fakeData
            this.$axios.get('guild/rpt/guild', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild: this.guildId,
                    gm: this.masterId,
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
                console.log(this.guildData)
                console.log('请求失败的返回值');
            });
        },
        resetForm(formName) {
            this.guildId = ""
            this.masterId = ''
            this.time = ""
            this.from = ''
            this.to = ''
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
        handleClick(id){
          this.$router.push({ path: '/editGuild', query: {id: id.id, name: id.name }})
        },
        giftExp(giftExp) {
            
        },
        getSummaries(param) {
            const { columns, data } = param;
            const sums = [];
            var livePlainTime = "00:00:00",liveDurationAll;
            var psPlainTime = "00:00:00",psDurationAll;
            data.forEach((data ,index) =>{
                // 時間換算
                let liveDuration=param.data[index].liveDuration
                var liHour = liveDuration.split(':')[0];
                var liMinutes = liveDuration.split(':')[1];
                var liSeconds = liveDuration.split(':')[2];

                let psDuration=param.data[index].psDuration
                var psHour = psDuration.split(':')[0];
                var psMinutes = psDuration.split(':')[1];
                var psSeconds = psDuration.split(':')[2];
                if(index == 0) {
                    liveDurationAll = moment(livePlainTime, "HH:mm:ss")
                                    .add(liSeconds, 'seconds')
                                    .add(liMinutes, 'minutes')
                                    .add(liHour, 'hour')
                                    .format('LTS');

                    psDurationAll = moment(psPlainTime, "HH:mm:ss")
                                    .add(psSeconds, 'seconds')
                                    .add(psMinutes, 'minutes')
                                    .add(psHour, 'hour')
                                    .format('LTS');                
                }
                else {
                    liveDurationAll = moment(liveDurationAll, "HH:mm:ss")
                                    .add(liSeconds, 'seconds')
                                    .add(liMinutes, 'minutes')
                                    .add(liHour, 'hour')
                                    .format('LTS');

                    psDurationAll = moment(psDurationAll, "HH:mm:ss")
                                    .add(psSeconds, 'seconds')
                                    .add(psMinutes, 'minutes')
                                    .add(psHour, 'hour')
                                    .format('LTS'); 
                }
               
            })
           
            console.log(liveDurationAll)
            columns.forEach((column, index) => {
                let thHeader=document.getElementsByTagName("th");
                let tdText=thHeader[index].innerText;
                if (index <=1 || index>11) {
                    sums[index] = '';
                    return;
                }
                if (index === 2) {
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
                    if (tdText.indexOf('/条')>=0){
                        sums[index] += ' /条'; 
                    }                   
                }else{
                    if(tdText.indexOf('直播时长')>=0) {
                        sums[index] = liveDurationAll.replace(/[a-zA-Z]+/, ""); 
                        return;
                    }else if(tdText.indexOf('1V1视频时长/收益')>=0) {
                        sums[index] = psDurationAll.replace(/[a-zA-Z]+/, ""); 
                        return;
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