<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span><span>公会贡献</span><span>檢視</span><span>主播贡献</span></div>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <div class="screen whiteBg">
                    <el-form-item label="时间：">
                        <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="排序：">
                        <el-select v-model="sort" placeholder="请选择">
                            <el-option
                            v-for="item in selectOption"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="getGuildContribution">搜索</el-button>
                        <el-button @click="resetForm">重置</el-button>
                    </el-form-item>
                </div>
                <div class="information whiteBg">
                    <el-table stripe :data="guildData" style="width: 100%">
                        <el-table-column width="130" prop="guildName" label="公会名称" ></el-table-column>
                        <el-table-column width="130" prop="guildName" label="主播昵称" ></el-table-column>
                        <el-table-column width="130" prop="guildId" label="主播ID"></el-table-column>
                        <el-table-column width="130" prop="giftExp" label="*礼物打赏"></el-table-column>
                        <el-table-column width="130" prop="ticketExp" label="*直播间门票"></el-table-column>
                        <el-table-column width="130" prop="psExp" label="*1V1视频计时"></el-table-column>
                        <el-table-column width="130" prop="lottoExp" label="*彩票盈利"></el-table-column>
                        <el-table-column width="130" prop="ugcExp" label="*UGC收益" ></el-table-column>
                        <el-table-column width="130" prop="ugcExp" label="*约会电台收益" ></el-table-column>
                        <el-table-column width="130" prop="pmExp" label="*私信收益"></el-table-column>
                        <el-table-column width="130" prop="total" label="*总收益"></el-table-column>
                        <el-table-column width="130" prop="commission" label="*公会抽成"></el-table-column>
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
            total: 0,
            pageNum: 0,
            pages: 0,
            pageSize: 10,
            selectOption: [{
                value: 'down',
                label: '总收益降序显示'
                }, {
                value: 'up',
                label: '总收益升序显示'
            }],
            fakeData: [
            {
                "guildId": 2,
                "guildName": "秋天的公會",
                "giftExp": 2560.0000,
                "ticketExp": 288.0000,
                "psExp": 0.0000,
                "lottoExp": 0,
                "ugcExp": 0,
                "pmExp": 0.0000,
                "total": 2848.0000,
                "commission": null,
                "uid": 1004,
                "nickname": "Housing 1"
            },
            {
                "guildId": 1,
                "guildName": "soth的公會",
                "giftExp": 306.0000,
                "ticketExp": 361.5000,
                "psExp": 0.0000,
                "lottoExp": 0,
                "ugcExp": 0,
                "pmExp": 1.0000,
                "total": 668.5000,
                "commission": null,
                "uid": 1003,
                "nickname": "king"
            },
            {
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "giftExp": 75.0000,
                "ticketExp": 242.0000,
                "psExp": 28.0000,
                "lottoExp": 0,
                "ugcExp": 0,
                "pmExp": 0.0000,
                "total": 345.0000,
                "commission": null,
                "uid": 1005,
                "nickname": "后裔"
            },
            {
                "guildId": 1,
                "guildName": "soth的公會",
                "giftExp": 0.0000,
                "ticketExp": 0.0000,
                "psExp": 50.0000,
                "lottoExp": 0,
                "ugcExp": 0,
                "pmExp": 0.0000,
                "total": 50.0000,
                "commission": null,
                "uid": 1001,
                "nickname": "admin"
            },
            {
                "guildId": 3,
                "guildName": "哈尼baby的公會",
                "giftExp": 0.0000,
                "ticketExp": 0.0000,
                "psExp": 0.0000,
                "lottoExp": 0,
                "ugcExp": 0,
                "pmExp": 0.5000,
                "total": 0.5000,
                "commission": null,
                "uid": 1006,
                "nickname": "后裔0"
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
            this.$axios.get('guild/rpt/perf/anchor', {
                params: {
                    from : this.from,
                    to: this.to,
                    sort: this.sort,
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
        }
    }
}
</script>