<template>
  <div>
    <div>
      <!-- 查询框 -->
      <div style="margin: 10px 0" class="three-input">
        <el-input style="width: 200px" placeholder="请输入姓名" prefix-icon="el-icon-search" v-model="username" v-on:keyup.enter.native="enterKey"></el-input>
        <el-input style="width: 200px" placeholder="请输入邮箱" prefix-icon="el-icon-message" v-model="email" v-on:keyup.enter.native="enterKey"></el-input>
        <el-input style="width: 200px" placeholder="请输入地址" prefix-icon="el-icon-position" v-model="address" v-on:keyup.enter.native="enterKey"></el-input>
        <el-button type="primary" v-on:click="search">搜 索</el-button>
        <el-button type="danger" v-on:click="reset">重 制</el-button>
      </div>

      <!-- 四个操作按钮 -->
      <div style="margin: 10px 0">
        <el-button type="primary" @click="handleAdd"><i class="el-icon-circle-plus-outline"></i><span>新 增</span></el-button>
        <el-popconfirm
            style="margin-left: 10px;"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定批量删除吗？"
            size="large"
            @confirm="delBatch"
            >
          <el-button type="danger" slot="reference"><i class="el-icon-remove-outline"></i><span>批量删除</span></el-button>
        </el-popconfirm>
        <el-upload :action="'http://' + serverIp + ':8090/user/import'" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
          <el-button type="warning" style="margin-left: 10px;"><i class="el-icon-upload"></i><span>导 入</span></el-button>
        </el-upload>
        <el-button type="success" @click="exp" style="margin-left: 10px;"><i class="el-icon-download"></i><span>导 出</span></el-button>
      </div>

      <!-- 表单 -->
      <el-table
      :data="tableData"
      border
      stripe
      :header-cell-class-name="headerBg"
      @selection-change="handleSelectionChange">
        <!-- checkbox 复选框 -->
        <el-table-column type="selection" width="39"></el-table-column>
        <!-- 主要信息 -->
        <el-table-column prop="id" label="ID" width="80" align="center"></el-table-column>
        <el-table-column prop="username" label="用户名" width="100" align="center"></el-table-column>
        <el-table-column prop="nickname" label="昵称" width="100" align="center"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="170" align="center"></el-table-column>
        <el-table-column prop="phone" label="电话" width="170" align="center"></el-table-column>
        <el-table-column prop="address" label="地址" align="center"></el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="handleEdit(scope.row)"><i class="el-icon-edit"></i><span>编 辑</span></el-button>
            <el-popconfirm
            style="margin-left: 10px;"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除这行记录吗？"
            size="large"
            @confirm="handleDelete(scope.row.id)"
            >
              <el-button type="danger" slot="reference"><i class="el-icon-remove-outline"></i><span>删 除</span></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!-- el-dialog 对话框 -->
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="40%" center>
        <el-form :model="form" label-width="70px" size="medium">
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
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" size="big">取 消</el-button>
          <el-button type="primary" @click="addSave" size="big">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 分页组件 -->
    <div>
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 8, 10, 20]"
        :page-size="8"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        style="margin: 30px 0 10px 50px"
      >
      </el-pagination>
    </div>

  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name:'userInfo',
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      // 背景
      headerBg: 'headerBg',
      // 分页
      total: 0,
      pageNum: 1,
      pageSize: 8,
      // 查询操作
      username: '',
      email: '',
      address: '',
      // 新增用户
      dialogFormVisible: false,
      form: {},
      // 批量删除
      multipleSelection: []
    };
  },
  created() {
    // 请求分页查询数据
    this.load();
  },
  methods: {
    // 加载页面数据
    load() {
    // axios方法发送request得到数据
    this.request
      .get('/user/page', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res => {
        console.log(res.data);
        this.tableData = res.data.records; //后端改用'Mybatis_plus'后把data改为records
        this.total = res.data.total;
      });

    // fetch方法发送请求得到数据
    // fetch("http://localhost:8090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username+"&email="+this.email+"&address="+this.address).then(res=>res.json()).then(res=>{
    //   console.log(res)
    //   this.tableData=res.data//后端改用'Mybatis_plus'后把data改为records
    //   this.total=res.total
    // })
    },
    // 重制按钮
    reset() {
      this.username = ''
      this.email = ''
      this.address = ''
      this.load();
    },
    // 分页 改变每页几条数据
    handleSizeChange(pageSize) {
      console.log(`每页 ${pageSize} 条`);
      this.pageSize = pageSize;
      this.load();
    },
    // 分页 改变当前页
    handleCurrentChange(pageNum) {
      console.log(`当前页: ${pageNum}`);
      this.pageNum = pageNum;
      this.load();
    },
    // 搜索按钮
    search() {
      console.log(this.pageNum);
      this.pageNum = 1 //非常重要 否则当查询非第一页数据时 查询出的记录会显示不出来
      this.load()
      console.log(this.pageNum);
    },
    // 聚焦搜索框时,按enter回车执行搜索按钮
    enterKey() {
      this.search()
    },
    // 添加
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    // 保存
    addSave() {
      this.request.post('/user', this.form).then(res => {
        if (res.data) {
          this.$message.success('保存成功')
          this.load()
        } else {
          this.$message.error('保存失败')
        }
      }),
      this.dialogFormVisible = false
    },
    // 编辑
    handleEdit(row) {
      this.form = Object.assign({}, row)//深拷贝，将row拷贝到空对象中，解决没点确定数据改变的问题
      this.dialogFormVisible = true
    },
    // 删除
    handleDelete(id) {
      this.request.delete('/user/' + id).then(res => {
        if (res.data) {
          this.$message.success('删除成功')
          this.load()
        } else {
          this.$message.error('删除失败')
        }
      });
    },
    // 批量删除
      // 复选框
    handleSelectionChange(val){
      console.log(val);
      this.multipleSelection = val
    },
      // 删除操作
    delBatch(){
      let ids=this.multipleSelection.map(v => v.id)
      this.request.post("/user/batch",ids).then(res => {
        if (res.data) {
          this.$message.success('批量删除成功')
          this.pageNum=1
          this.load()
        } else {
          this.$message.error('批量删除失败')
        }
      })
    },
    // 导出
    exp(){
      window.open("http://localhost:8090/user/export")
    },
    // 导入
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  },
}
</script>

<style>
.headerBg {
  background: #eee !important;
}
.three-input div {
  margin-right: 10px;
}
</style>