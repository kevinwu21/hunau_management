<template>
  <el-row>
    <el-col :span="11.8" style="margin-left:30px;margin-top: 5px;">
      <div id="" style="width: 545px;height:310px;padding: 0;">
        <el-row :gutter="20">
          <el-col :span="12" style="margin-bottom: 20px;margin-top: 2px;">
            <el-card style="height: 110px;border-radius: 10px; color:#409EFF">
              <div><i class="el-icon-user"></i> 用户总数</div>
              <div style="padding: 10px 0; text-align:center; font-weight:bold">
                552
              </div>
            </el-card>
          </el-col>
          <el-col :span="12" style="margin-bottom: 20px;margin-top: 2px;">
            <el-card style="height: 110px;border-radius: 10px;color:#67C23A">
              <div><i class="el-icon-shopping-cart-2"></i> 销售总数</div>
              <div style="padding: 10px 0; text-align:center; font-weight:bold">
                621
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card style="height: 110px;border-radius: 10px;color:#E6A23C">
              <div><i class="el-icon-coin"></i> 收益总额</div>
              <div style="padding: 10px 0; text-align:center; font-weight:bold">
                8237649
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card style="height: 110px;border-radius: 10px;color:#F56C6C">
              <div><i class="el-icon-map-location"></i> 门店总数</div>
              <div style="padding: 10px 0; text-align:center; font-weight:bold">
                8396
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-col>
    <el-col :span="11.9" style="margin-left:50px;margin-top: 5px;">
      <el-card shadow="always" style="border-radius: 30px;">
        <div id="pie" style="width: 510px;height:560px;"></div>
      </el-card>
    </el-col>
    <el-col :span="11.8" style="margin:-310px 25px;">
      <el-card shadow="always" style="border-radius: 30px;">
        <div id="line" style="width: 510px;height:300px;margin-top: 5px;margin-bottom: -35px;"></div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import * as echarts from 'echarts'
export default{
  data(){
    return{

    }
  },
  mounted() {
    this.getLine()
    this.getPie()
  },
  methods: {
    // 折线图
    getLine(){
      var chartDom = document.getElementById('line');
      var myChart = echarts.init(chartDom);
      var option = {
        title: {
          text: '各季度注册用户数量统计',
          subtext: '趋势图',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
        },
        xAxis: {
          type: 'category',
          data: ["第一季度","第二季度","第三季度","第四季度"]
        },
        yAxis: {
          type: 'value'
        },
        series:
          [
          {
            data: [],
            type: 'line'
          },
          {
            data: [],
            type: 'bar'
          }
        ]
      };
      this.request.get("/echarts/members").then(res => {
        // 填入数据
        option.series[0].data = res.data
        option.series[1].data = res.data
        // 数据准备完毕后再 set
        myChart.setOption(option);
      })
    },
    // 饼状图
    getPie(){
      var chartDom = document.getElementById('pie');
      var myChart = echarts.init(chartDom);
      var option = {
        title: {
          text: '各季度注册用户数量统计',
          subtext: '比例图',
          left: 'center',
          top:'2%'
        },
        tooltip: {
          trigger: 'item',
        },
        legend: {
          orient: 'vertical',
          right: 'right',
          bottom:'0%',
        },
        series: [
          {
            type: 'pie',
            radius: '60%',
            top:'0%',
            // 饼图上的文本标签
            label:{
              normal:{
                show:true,
                position:'outside', // 文本的位置
                textStyle: {
                  fontWeight: 300,
                  fontSize: 16,
                },
                formatter: '{d}%'
              }
            },
            data: [], // 填入数据
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      this.request.get("/echarts/members").then(res => {
        // 填入数据
        option.series[0].data = [
          {name: '第一季度',value: res.data[0]},
          {name: '第二季度',value: res.data[1]},
          {name: '第三季度',value: res.data[2]},
          {name: '第四季度',value: res.data[3]}
        ]
        // 数据准备完毕后再 set
        myChart.setOption(option);
      })
    }
  },
}
</script>

<style>

</style>