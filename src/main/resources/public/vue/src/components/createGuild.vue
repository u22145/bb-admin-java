<template>
  <div class="multipleInput">
    <div class="Breadcrumbs">
      <span>公会管理</span>
      <span>公会列表</span>
      <span>新建公会</span>
    </div>
    <!-- input start -->
    <div class="information whiteBg">
      <el-form
        :rules="rules"
        :label-position="labelPosition"
        label-width="28%"
        status-icon
        :model="numberValidateForm"
        ref="numberValidateForm"
        class="demo-ruleForm"
      >
        <!-- 公会名称： -->
        <el-form-item
          label="公会名称："
          prop="name"
          :rules="[
                        { required: true, message: '栏位不能为空', trigger: 'blur'},
                    ]"
        >
          <el-input
            type="name"
            placeholder="请输入"
            v-model="numberValidateForm.name"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <!-- 会长ID： -->
        <el-form-item
          label="会长ID："
          prop="masterId"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须为数字值'}
                    ]"
        >
          <el-input
            type="masterId"
            placeholder="请输入数字值"
            v-model.number="numberValidateForm.masterId"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <!-- 礼物抽成： -->
        <el-form-item
          label="礼物抽成："
          prop="giftCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="giftCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.giftCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 彩票盈利抽成： -->
        <el-form-item
          label="彩票盈利抽成："
          prop="lotteryCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="lotteryCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.lotteryCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 1V1视频抽成： -->
        <el-form-item
          label="1V1视频抽成："
          prop="privateStreamCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="privateStreamCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.privateStreamCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 直播间门票抽成： -->
        <el-form-item
          label="直播间门票抽成："
          prop="streamTicketCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="streamTicketCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.streamTicketCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- UGC抽成： -->
        <el-form-item
          label="UGC抽成："
          prop="ugcCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="ugcCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.ugcCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 约会电台抽成： -->
        <el-form-item
          label="约会电台抽成："
          prop="datingCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'}
                    ]"
        >
          <el-input
            type="datingCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.datingCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 私信抽成： -->
        <el-form-item
          label="私信抽成："
          prop="messageCommission"
          :rules="[
                        { required: true, message: '栏位不能为空'},
                        { type: 'number', message: '栏位必须介于0～100之间的数字值'},
                    ]"
        >
          <el-input
            type="messageCommission"
            placeholder="请输入须介于0～100之间的數值"
            v-model.number="numberValidateForm.messageCommission"
            autocomplete="off"
          >
            <template slot="append">%</template>
          </el-input>
        </el-form-item>
        <!-- 状态： -->
        <el-form-item label="状态：">
          <el-select v-model="value" placeholder="请选择状态">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- buttom start -->
        <el-row>
          <el-button type="primary" @click="submitForm('numberValidateForm')">提交</el-button>
          <el-button @click="goBack" type="info">取消</el-button>
          <el-button @click="resetForm('numberValidateForm')">重置</el-button>
        </el-row>
        <!-- buttom end -->
      </el-form>
      <!-- input end -->
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelPosition: "right",
      value: "1",
      numberValidateForm: {
        messageCommission: "",
        datingCommission: "",
        ugcCommission: "",
        streamTicketCommission: "",
        privateStreamCommission: "",
        lotteryCommission: "",
        giftCommission: "",
        masterId: "",
        name: "",
      },
      options: [
        {
          value: "1",
          label: "正常",
        },
        {
          value: "0",
          label: "禁止",
        },
      ],
    };
  },
  methods: {
    submit() {
      this.$axios
        .post("guild/", {
          name: this.numberValidateForm.name,
          masterId: this.numberValidateForm.masterId,
          giftCommission: this.numberValidateForm.giftCommission / 100,
          lotteryCommission: this.numberValidateForm.lotteryCommission / 100,
          privateStreamCommission:
            this.numberValidateForm.privateStreamCommission / 100,
          streamTicketCommission:
            this.numberValidateForm.streamTicketCommission / 100,
          ugcCommission: this.numberValidateForm.ugcCommission / 100,
          datingCommission: this.numberValidateForm.datingCommission / 100,
          messageCommission: this.numberValidateForm.messageCommission / 100,
          status: this.value,
        })
        .then(
          function (response) {
            console.log(response);
            if (
              response.data.status == 1 &&
              response.data.data == "OK" &&
              response.data.msg == ""
            ) {
              // show edit guild success
              console.log("成功");
              this.$message({
                message: "提交成功",
                type: "success",
              });
              setTimeout(
                function () {
                  this.$router.push("/guildList");
                }.bind(this),
                1500
              );
            } else {
              console.log("北七二號");
              this.$message.error(response.data.msg);
            }
          }.bind(this)
        )
        .catch(function (error) {
          console.log(error);
        });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.submit();
        } else {
          this.$message.error("输入值须介于0～100之间或未填写必填栏位");
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    goBack() {
      this.$router.push("/guildList");
    },
  },
  mounted() {},
};
</script>