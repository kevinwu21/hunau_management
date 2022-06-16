<template>
  <el-card style="width:700px;margin: 70px auto;border-radius: 20px;padding-top: 35px;">
    <el-form :model="form" label-width="70px" size="large" style="margin-left: 30px;">
      <el-form-item label="用户名">
        <el-input style="width: 300px;" v-model="form.username" autocomplete="off" placeholder="只可输入大小英文字母/下划线" prefix-icon="el-icon-user"></el-input>
          <el-upload
        class="avatar-uploader"
        action="http://localhost:8090/file/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess">
        <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      </el-form-item>
      <el-form-item label="昵称" style="margin-top:-64px;width: 400px;">
        <el-input style="width: 300px;" v-model="form.nickname" autocomplete="off" placeholder="输入中文或英文昵称" prefix-icon="el-icon-more-outline"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input style="width: 490px;" v-model="form.email" autocomplete="off" placeholder="输入个人邮箱号" prefix-icon="el-icon-message"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input style="width: 490px;" v-model="form.phone" autocomplete="off" placeholder="输入11位电话号码" maxlenth="11" minlenth="11" prefix-icon="el-icon-phone-outline"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input style="width: 490px;" v-model="form.address" autocomplete="off" placeholder="输入详细地址" prefix-icon="el-icon-location-information"></el-input>
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
    this.getUser().then(res=>{
      console.log(res)
      this.form = res
    })
  },
  methods: {
    async getUser(){
      return (await this.request.get("/user/username/"+this.user.username)).data
    },
    // 保存
    addSave() {
      this.request.post('/user', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('保存成功')
          //更新浏览器存储的用户信息
          this.getUser().then(res=>{
            res.token = JSON.parse(localStorage.getItem("user")).token
            localStorage.setItem("user",JSON.stringify(res))
          })
        } else {
          this.$message.error('保存失败')
        }
      })
    },
    // 取消
    cancel(){
      this.$router.push("/homePage")
    },
    // 头像上传
    handleAvatarSuccess(res){
      this.form.avatarUrl = res
    }
  },
}
</script>

<style>
 .avatar-uploader{
  text-align:center;
  display: flex;
  float: right;
  margin-right: 70px;
 }
 .avatar-uploader .el-upload {
    border: 2px solid #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    border-radius: 20px;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
    border-radius: 20px;
  }
</style>
