<template>
    <div>
        <div class="Breadcrumbs"><span>公会管理</span><span>公会数据报表</span></div>
        <div class="screen whiteBg"><el-row>
            <el-col :span="24" :push="3">
                <el-button type="primary" plain>今日</el-button>
                <el-button>昨日</el-button>
                <el-button>本周</el-button>
                <el-button>本月</el-button>
                <el-button>上月</el-button>
                <el-date-picker style="margin: 0 10px;"
                    v-model="time"
                     @change="handleChange"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                </el-date-picker>
                <el-button type="primary" @click="getGuildReport">搜索</el-button>
            </el-col>
        </el-row>
        </div>
                <div class="information whiteBg">
        <el-row class="fiveGroup" type="flex" justify="space-between">
            <div class="FG borderStyle"><span>公会个数</span><strong>124,345</strong></div>
            <div class="FG borderStyle"><span>主播人数</span><strong>124,345</strong></div>
            <div class="FG borderStyle"><span>主播在线人数</span><strong>124,345</strong></div>
            <div class="FG borderStyle"><span>新增主播</span><strong>124,345</strong></div>
            <div class="FG borderStyle"><span>新增主播</span><strong>124,345</strong></div>
        </el-row>
        <el-row type="flex" justify="space-between">
            <el-col class="borderStyle leftGroup">
                <a href="#" class="details">詳情</a>
                <p>
                    <strong>总直播时长：</strong><span>{{guildStat.liveDuration}}</span></p>
                <p>
                    <strong>总门票收益：</strong><span>{{guildStat.ticketExp}}</span> / <span>{{guildStat.ticketBuyers}}</span>人</p>
                <p>
                    <strong>总1V1视频时长：</strong><span>{{guildStat.psDuration}}</span></p>
                <p>
                  <strong>总1V1收益：</strong><span>{{guildStat.psExp}}</span> / <span>{{guildStat.psCnt}}</span></p>
                <p>
                    <strong>总礼物打赏：</strong><span>{{guildStat.giftExp}}</span> / <span>{{guildStat.giftBuyers}}</span>人</p>
                <p>
                    <strong>总彩票盈利：</strong><span>{{guildStat.lottoExp}}</span> / 投注 <span>{{guildStat.lottoCnt}}</span> / 中奖<span>{{guildStat.lottoWonCnt}}</span></p>
                <p>
                    <strong>总UGC收益：</strong><span>{{guildStat.ugcExp}}</span> / <span>{{guildStat.ugcCnt}}</span>人</p>
                <p>
                    <strong>总约会电台收益：</strong><span>{{guildStat.datingExp}}</span> / <span>{{guildStat.datingCnt}}</span>人</p>
                <p>
                    <strong>总私信收益：</strong><span>{{guildStat.pmExp}}</span> / <span>{{guildStat.pmCnt}}</span>條 / <span>{{guildStat.pmSenders}}</span> 人</p>
            </el-col>
            <el-col class="borderStyle rightGroup">
                <a href="#" class="details">詳情</a>
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="公会贡献" name="first">
                        <el-table
                            :data="guildRanking"
                            stripe
                            style="width: 100%">
                          <el-table-column
                            type="index"
                            width="180"
                            label="序号"
                          >
                          </el-table-column>
                            <el-table-column
                            prop="entity"
                            label="公会名称"
                            width="180">
                            </el-table-column>
                            <el-table-column
                            prop="platformShare"
                            label="平台收益">
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane label="主播贡献" name="second">
                        <el-table
                            :data="anchorRanking"
                            stripe
                            style="width: 100%">
                            <el-table-column
                            type="index"
                            label="序号"
                            width="180">
                            </el-table-column>
                            <el-table-column
                            prop="entity"
                            label="主播昵称"
                            width="180">
                            </el-table-column>
                            <el-table-column
                            prop="platformShare"
                            label="平台收益">
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                </el-tabs>
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
        anchorRanking: null,
        guildRanking: null,
        guildStat: null,
        activeName: 'second',
        time: "",
        from: "",
        to: "",
        tableData1: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
        tableData2: [{
          date: '2016-05-02',
          name: '陳小美',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '陳小美',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '陳小美',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '陳小美',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      };
    },
    methods: {
      getGuildReport() {
          this.$axios.get('guild/rpt/overview', {
                params: {
                    from : this.from,
                    to: this.to
                }
            })
            .then(function (response) {
                this.anchorRanking = response.data.data.anchorRankings
                this.guildRanking = response.data.data.guildRankings
                this.guildStat = response.data.data.stats
            }.bind(this))
            .catch(function (error) {
                console.log('请求失败的返回值');
            });
      },
      handleChange() {
        this.from = moment(this.time[0].toString()).format('yyyyMMDD')
        this.to =moment(this.time[1].toString()).format('yyyyMMDD')
      }
    },
    mounted() {
      this.getGuildReport()
    }
  };
</script>