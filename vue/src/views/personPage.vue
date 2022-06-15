<template>
  <el-card style="width:700px;margin: 70px auto;border-radius: 20px;padding-top: 35px;">
    <el-form :model="form" label-width="70px" size="large">
      <el-form-item label="用户名">
        <el-input style="width: 235px;" v-model="form.username" autocomplete="off" placeholder="只可输入大小英文字母/下划线" prefix-icon="el-icon-user"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input style="width: 235px;" v-model="form.nickname" autocomplete="off" placeholder="输入中文或英文昵称" prefix-icon="el-icon-more-outline"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input style="width: 430px;" v-model="form.email" autocomplete="off" placeholder="输入个人邮箱号" prefix-icon="el-icon-message"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input style="width: 430px;" v-model="form.phone" autocomplete="off" placeholder="输入11位电话号码" maxlenth="11" minlenth="11" prefix-icon="el-icon-phone-outline"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input style="width: 430px;" v-model="form.address" autocomplete="off" placeholder="输入详细地址" prefix-icon="el-icon-location-information"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="cancel" size="big">取 消</el-button>
        <el-button type="primary" @click="addSave" size="big">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default{
  data() {
    return {
        form:{},
        user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    };
  },
  created() {
    this.request.get("/user/username/"+this.user.username).then(res => {
      if(res.code === '200'){
        this.form = res.data
      }
    })
  },
  methods: {
    // 保存
    addSave() {
      this.request.post('/user', this.form).then(res => {
        if (res.data) {
          this.$message.success('保存成功')
          this.$router.push("/homePage")
        } else {
          this.$message.error('保存失败')
        }
      })
    },
    // 取消
    cancel(){
      this.$router.push("/homePage")
    }
  },
}
</script>

<style>

</style>
