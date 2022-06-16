<template>
  <div>
      <!-- 搜索栏 -->
      <div style="margin: 10px 0">
        <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name" v-on:keyup.enter.native="enterKey"></el-input>
        <el-button class="ml-5" type="primary" @click="search" style="margin-left: 10px;">搜 索</el-button>
        <el-button type="warning" @click="reset">重 置</el-button>
      </div>
      <!-- 操作按钮 -->
      <div style="margin: 10px 0">
        <el-upload :action="'http://' + serverIp + ':8090/file/upload'" :show-file-list="false" :on-success="handleFileUploadSuccess" style="display: inline-block">
          <el-button type="success"><i class="el-icon-upload"></i><span>上传文件</span></el-button>
        </el-upload>
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
        <el-table-column prop="name" label="文件名称" width="400" align="center"></el-table-column>
        <el-table-column prop="type" label="文件类型" width="100" align="center"></el-table-column>
        <el-table-column prop="size" label="文件大小(KB)" width="100" align="center"></el-table-column>
        <el-table-column label="预览" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="preview(scope.row.url)">预 览</el-button>
          </template>
        </el-table-column>
        <el-table-column label="下载" align="center">
          <template slot-scope="scope">
            <el-button type="primary" @click="download(scope.row.url)">下 载</el-button>
          </template>
        </el-table-column>
        <el-table-column label="启用" align="center">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"
              @change="changeEnable(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
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
export default{
  data() {
    return {
      tableData:[],
      name:'',
      multipleSelection:[],
      serverIp: serverIp,
      // 背景
      headerBg: 'headerBg',
      pageNum:1,
      pageSize:8,
      total:0,
    };
  },
  created(){
    this.load();
  },
  methods: {
    // 加载页面数据
    load() {
    // axios方法发送request得到数据
    this.request
      .get('/file/page', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
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
      this.name = ''

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
    // 删除
    handleDelete(id) {
      this.request.delete('/file/' + id).then(res => {
        if (res.code === '200') {
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
      this.request.post("/file/batch",ids).then(res => {
        if (res.code === '200') {
          this.$message.success('批量删除成功')
          this.pageNum=1
          this.load()
        } else {
          this.$message.error('批量删除失败')
        }
      })
    },
    handleFileUploadSuccess(res){
      console.log(res);
      this.$message.success("上传成功")
      this.load()
    },
    download(url) {
      window.open(url)
    },
    preview(url) {
      window.open('https://file.keking.cn/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))
    },
    // 更新操作
    changeEnable(row) {
      this.request.post("/file/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        }
      })
    },

  },

}
</script>

<style>
.headerBg {
  background: #eee !important;
}
</style>