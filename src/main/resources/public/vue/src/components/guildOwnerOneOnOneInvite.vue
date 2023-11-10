<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理系统</span><span>报表管理</span><span>1v1视频报表</span><span>詳情</span></div>
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
                    <el-table  show-summary :summary-method="getSummaries" stripe :data="liveData" style="width: 100%">
                        <el-table-column width="130" prop="fromDT" label="开始时间" ></el-table-column>
                        <el-table-column width="130" prop="toDT" label="结束时间"></el-table-column>
                        <el-table-column width="130" prop="nickname" label="主播昵称"></el-table-column>
                        <el-table-column width="130" prop="uid" label="主播ID"></el-table-column>
                        <el-table-column width="130" prop="duration" label="*1V1视频时长"></el-table-column>
                        <el-table-column width="130" prop="chargePerMin" label="*单价/分"></el-table-column>
                        <el-table-column width="130" prop="streamExp" label="*时长计费" ></el-table-column>
                        <el-table-column width="130" prop="giftExp" label="*礼物打赏">
                        <template slot-scope="scope">
                                <el-button @click="giftExp = true" type="text">{{scope.row.giftExp}}</el-button>
                            </template>
                        </el-table-column>
                        <el-table-column width="130" prop="total" label="*总收益"></el-table-column>
                        <el-table-column width="130" prop="lottoExp" label="*公屏条数"></el-table-column>
                        <el-table-column width="130" prop="total1" label="*用户ID"></el-table-column>
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
            fakeData: [
            {
               "uid": 1001,
                "nickname": "admin",
                "guildId": 1,
                "guildName": "soth的公會",
                "duration": "00:21:20",
                "chargePerMin": 0.0391,
                "streamExp": 50.0000,
                "giftExp": 0.0000,
                "total": 50.0000,
                "fromDT": "2020-08-19T15:10:30.000+00:00",
                "toDT": "2020-08-19T15:10:30.000+00:00"
            }
            ],
            giftExp:false,
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
            this.liveData = this.fakeData
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
        handleClick(data) {
            // direct to streamer
        },
        backTo(data) {
            this.$router.push('/guildOwnerOneOnOneReport')
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
            // console.log(tdText)
            if (index <=2 ||index>9) {
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