<template>
    <div>
       <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span><span>1V1视频详情</span></div>
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
                    <el-table show-summary :summary-method="getSummaries" stripe :data="liveData" style="width: 100%">
                        <el-table-column prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column prop="uid" label="主播ID"></el-table-column>
                        <el-table-column prop="durationTotal" label="1V1视频时长"></el-table-column>
                        <el-table-column prop="streamTotal" label="时长计费"></el-table-column>
                        <el-table-column prop="giftTotal" label="公屏条数"></el-table-column>
                        <el-table-column prop="giftTotal" label="礼物打赏"></el-table-column>
                        <el-table-column prop="streamCnt" label="场次" ></el-table-column>
                        <el-table-column prop="total" label="总收益"></el-table-column>
                        <el-table-column prop="" label="操作">
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
                "uid": 1001,
                "nickname": "admin",
                "durationTotal": "00:21:20",
                "giftTotal": 0.0000,
                "streamTotal": 50.0000,
                "total": 50.0000,
                "streamCnt": 1
            },
            {
                "uid": 1005,
                "nickname": "后裔",
                "durationTotal": "00:08:20",
                "giftTotal": 0.0000,
                "streamTotal": 28.0000,
                "total": 28.0000,
                "streamCnt": 1
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
            // this.liveData = this.fakeData
            this.$axios.get('guild/rpt/ps', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guildId : this.guildId,
                    sort : this.sort

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
            this.sort = ''
            this.guildId = ''
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
            this.$router.push({ path: '/oneOnoneDetail', query: {id: data.uid}})
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
                let thHeader=document.getElementsByTagName("th");
                let tdText=thHeader[index].innerText;
                if (index <=0 || index>7) {
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
                }else{
                    if(tdText.indexOf('1V1视频时长')>=0) {
                        sums[index] = liveDurationAll.replace(/[a-zA-Z]+/, ""); 
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