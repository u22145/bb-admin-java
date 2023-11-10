<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>总数据详情</span><span>ugc收益详情</span></div>
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
                        <el-table-column width="135" prop="nickname" label="主播昵称" ></el-table-column>
                        <el-table-column width="135" prop="guildId" label="主播ID"></el-table-column>
                        <el-table-column width="140" prop="videoIncome" label="社区视频收益/人">
                            <template slot-scope="scope">
                                <el-button @click="videoIncome = true" type="text">{{scope.row.videoIncome}}</el-button>
                            </template>   
                        </el-table-column>
                        <el-table-column width="140" prop="imgIncome" label="社区图片收益/人">
                            <template slot-scope="scope">
                                <el-button @click="imgIncome = true" type="text">{{scope.row.imgIncome}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="140" prop="fictionIncome" label="社区小说收益/人">
                            <template slot-scope="scope">
                                <el-button @click="fictionIncome = true" type="text">{{scope.row.fictionIncome}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="140" prop="PhotoAlbumIncome" label="相册隐私收益/人">
                            <template slot-scope="scope">
                                <el-button @click="PhotoAlbumIncome = true" type="text">{{scope.row.PhotoAlbumIncome}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="140" prop="loveIncome" label="啪啪短视频收益/人">
                            <template slot-scope="scope">
                                <el-button @click="loveIncome = true" type="text">{{scope.row.loveIncome}}</el-button>
                            </template> 
                        </el-table-column>
                        <el-table-column width="135" prop="total" label="总收益"></el-table-column>
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
            <el-dialog title="社区视频收益/人" :visible.sync="videoIncome">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*内容"></el-table-column>
                    <el-table-column prop="nickname" label="*价格"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
            <el-dialog title="社区图片收益/人" :visible.sync="imgIncome">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*内容"></el-table-column>
                    <el-table-column prop="nickname" label="*价格"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
            <el-dialog title="社区小说收益/人" :visible.sync="fictionIncome">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*标题"></el-table-column>
                    <el-table-column prop="nickname" label="*价格"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
            <el-dialog title="相册隐私收益/人" :visible.sync="PhotoAlbumIncome">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*图片"></el-table-column>
                    <el-table-column prop="nickname" label="*价格"></el-table-column>
                </el-table>
                <el-pagination
                @current-change="handleCurrentChange"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
                </el-pagination>
            </el-dialog>
            <el-dialog title="啪啪短视频收益/人" :visible.sync="loveIncome">
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="startedAt" label="*购买时间" ></el-table-column>
                    <el-table-column prop="uid" label="*用户ID"></el-table-column>
                    <el-table-column prop="nickname" label="*视频"></el-table-column>
                    <el-table-column prop="nickname" label="*价格"></el-table-column>
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
                "videoIncome": 2.0000,
                "imgIncome": 121,
                "fictionIncome": 242.0000,
                "PhotoAlbumIncome": 3,
                "loveIncome": 75.0000,
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
                "videoIncome": 2.0000,
                "imgIncome": 121,
                "fictionIncome": 242.0000,
                "PhotoAlbumIncome": 3,
                "loveIncome": 75.0000,
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
                "videoIncome": 2.0000,
                "imgIncome": 121,
                "fictionIncome": 242.0000,
                "PhotoAlbumIncome": 3,
                "loveIncome": 75.0000,
                "lottoExp": 0.0000,
                "total": 317.0000,
                "viewers": 121,
                "peakViewers": 99,
                "startedAt": "2020-08-20 08:00",
                "closedAt": "2020-08-20 08:00"
            }],
            videoIncome:false,
            imgIncome:false,
            fictionIncome:false,
            PhotoAlbumIncome:false,
            loveIncome:false,
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
        this.total= response.data.data.total
        this.pageNum= response.data.data.pageNum
        this.pages= response.data.data.pages
        this.pageSize= response.data.data.pageSize
    
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            // cause now we don't have data to search so use fake data
            this.liveData =this.fakeData
            this.$axios.get('guild/rpt/live/{detail}', {
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
            this.from = moment(this.time[0].toString()).format('yyyyMMdd')
            this.to =moment(this.time[1].toString()).format('yyyyMMdd')
        },
        handleClick(data) {
            // direct to streamer
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
            if (index <=0) {
                sums[index] = '';
                return;
            }else if(index === 1){
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
                
                    sums[index] = 'NaN'; 
            }
        });

        return sums;
        }
    }
}
</script>
