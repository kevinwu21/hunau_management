<template>
  <div>
    <div style="display: flex;justify-content: center;margin-top: 50px;">
      <img src="../assets/logo.png">
    </div>
    <br>
    <br>
    <div style="display: flex;justify-content: center;">
      <h2 style="font-size: 40px;color: #43b883;">后台管理系统</h2>
    </div>
    <br>
    <div style="display: flex;justify-content: center;">
      <h2 style="font-size: 30px;color: #ffc312;">首页</h2>
    </div>
    <br>
    <div style="display: flex;justify-content: center;">
      <h2 style="font-size: 30px;color: #409eff;">Vue 2 + Element UI + Spring Boot</h2>
    </div>
    <br>
    <div style="display: flex;justify-content: center;">
    <el-button @click="handlePush" type="success" size="danger" style="border-radius: 10px;" to="/userInfo">
      <h2 style="font-size: 20px;">个人信息</h2>
    </el-button>
    </div>
  </div>
</template>

<script>
  export default{
    methods: {
      handlePush(){
        this.$router.push('/personPage')
      }
    },
  }
</script>

<style>

</style>