<template>
    <div>
       <h2>直播间详情</h2>
       <div>
           <el-form class="demo-form-inline" :inline="true" :label-position="labelPosition" >
                <el-form-item label="时间：">
                    <el-date-picker v-model="time" @change="handleChange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
                </el-form-item>
                <el-form-item label="公会ID：">
                    <el-input v-model="guildId" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="有无公会：">
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
                <el-table stripe :data="liveData" style="width: 100%">
                    <el-table-column prop="nickname" label="主播昵称" ></el-table-column>
                    <el-table-column prop="uid" label="主播ID"></el-table-column>
                    <el-table-column prop="durationTotal" label="直播时长"></el-table-column>
                    <el-table-column prop="ticketExp" label="门票收益/人"></el-table-column>
                    <el-table-column prop="giftExp" label="礼物打赏/人"></el-table-column>
                    <el-table-column prop="lotteryExp" label="彩票盈利"></el-table-column>
                    <el-table-column prop="viewers" label="进入直播间人次" ></el-table-column>
                    <el-table-column prop="total" label="总收益"></el-table-column>
                    <el-table-column prop="avgViewers" label="平均在线人数"></el-table-column>
                    <el-table-column prop="" label="操作">
                      <template slot-scope="scope">
                          <el-button @click="handleClick(scope.row)" type="text">详情</el-button>
                      </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="100"
                    layout="prev, pager, next, jumper"
                    :total="1000">
                </el-pagination>
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
            ]
        }
    },
    mounted() {
        this.getGuildContribution()
    },
    methods: {
        getGuildContribution() {
            this.$axios.get('guild/rpt/live', {
                params: {
                    from : this.from,
                    to: this.to,
                    page: this.page,
                    guild: this.guildId
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
        handleClick(data) {
            // direct to streamer
        }
    }
}
</script>