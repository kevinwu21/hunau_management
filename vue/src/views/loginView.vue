<template>
  <div class="wrapper">
    <div style="position: absolute;
    top:50%;
    left:50%;
    transform: translate(-50%,-50%);
    background-color: #fff;
    width: 340px;
    height: 380px;
    padding: 23px;
    border-radius: 25px;
    box-shadow:10px 10px 100px #555555dd;">
      <div style="margin: 35px 0 40px 0; text-align: center; font-size: 22px;font-weight: lighter;">
        <img src="../../public/UncleKevin.png" style="max-width: 50px;margin-top: -15px;margin-bottom: 10px;">
        <p style="font-weight: 350; color: #1f2d3d;font-size: 20px;">后台管理系统</p>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="large" style="margin: 5px 0 5px 0" prefix-icon="el-icon-user" placeholder="用户名" v-model="user.username" @keyup.enter.native="enter('username')"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="large" style="margin: 3px 0 10px 0" prefix-icon="el-icon-lock" placeholder="密码" show-password v-model="user.password" @keyup.enter.native="enter('submit')" ref="password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 20px 0; text-align: right">
          <el-button type="success" size="medium"  autocomplete="off" @click="$router.push('/register')">注册</el-button>
          <el-button type="primary" size="medium"  autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "loginView",
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user/login", this.user).then(res => {
            if(res.code === '200') {
              //存储用户信息到浏览器
              localStorage.setItem("user",JSON.stringify(res.data))
              this.$router.push("/")
              this.$message.success("登陆成功")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    // 键盘事件（enter）
    enter(obj) {
      if (obj === 'username') {
        this.$nextTick(() => {
          this.$refs.password.focus()
        })
      }
      if (obj === 'submit') {
        this.login()
      }
    }
  }
}
</script>

<style>
  .wrapper {
    height: 100vh;
    background-image:linear-gradient(to bottom right, #FC466B , #3F5EFB);
    overflow: hidden;
  }
</style>

