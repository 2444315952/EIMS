<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
  <div id="d">
    <div id="d1" class="lt-flex-level" style="padding: 10px 0;">
      <div class=" lt-item1">
        &nbsp; <span style="color:red">*</span>供应商：
        <el-input v-model="form.supplierName" disabled class="input-with-select xin" style="width: 190px;">
        </el-input>
        
      </div>
      <div class=" lt-item1 w200" >
        &nbsp;业务员：
        <el-select @click="clickEmployeeSelect()" class="xin" @change="changeEmployeeSelect"
									v-model="form.employeeName" style="width: 206px;" placeholder="请选择业务员">
									<el-option v-for="e in employeeSelectValue" :label="e.employeeName"
										:value="e.employeeName"></el-option>
								</el-select>

      </div>
      <div class=" lt-item1 " >
        单据编号：<el-input style="width: 188px;" v-model="form.payDocunum "  placeholder="(空)" disabled class="xin" >
         
        </el-input>
        
      </div>
     
      
      
    </div>
    <div class="lt-flex-level" style="padding: 10px 0;border: 0px solid #03ff2d;">
      <div class=" lt-item1 " >
        结算方式：
        <el-select v-model="form.clearingForm"  placeholder="请选择" class="xin">
          <el-option class="el-input--suffix" v-for="item in options" :key="item.value" :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>

      <div style="text-align: left;" class=" lt-item1 w200" >
        采购退货单号：
        <el-input placeholder="(空)" disabled v-model="form.purchDocunum " class="input-with-select" style="width: 190px;margin-top: 4px;">
          <template #append>
            <el-button icon="el-icon-more" @click="form.dialogFormVisible = true;loadData()"></el-button>
          </template>
        </el-input>
        <el-dialog title="选择采购退货单" v-model="form.dialogFormVisible">
          <el-form :model="form">

            <div class=" lt-flex-left">
              <div class=" w83">

              </div>
              <div class=" w300">
                <el-form-item style="width: 300px;">
                  <el-input v-model="purchase.searchInput"  placeholder="采购退货单号/供应商名称/业务员名">
                  <template #append>
                   <el-button type="primary" @click="handleSearch">查询</el-button>
                  </template>
                  </el-input>
                </el-form-item>

              </div>
              <div class="w79">
               
              </div>

            </div>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="PaymentConfirmButton">确 定</el-button>
            </span>
          </template>
          <el-table :data="purchase.tableData" highlight-current-row @current-change="PurchaseSelectionChange">
            <el-table-column prop="purchReturnDocunum" label="采购退货单号" width="170"></el-table-column>
            <el-table-column prop="supplierName" label="供应商名"></el-table-column>
            <el-table-column property="employeeName" label="业务员名" width="150"></el-table-column>
            <el-table-column property="warehouseName" label="仓库名" width="200"></el-table-column>
            <el-table-column property="audited" label="是否审核"></el-table-column>
            <el-table-column property="refundAmount" label="退款金额"></el-table-column>
          </el-table>
          <el-footer>
				  <div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
						:page-sizes="[10,20,40,80]" :page-size="purchase.pageParam.pageSize"
						layout="total, sizes, prev, pager, next, jumper" :total="purchase.tableTotal">
					</el-pagination>
				  </div>
			    </el-footer>
        </el-dialog>
      </div>
      <div class=" lt-item1 ">
        单据日期：
        <el-date-picker class="xin1" style="width:190px" v-model="form.documentDate"  type="date" placeholder="选择日期">
        </el-date-picker>
      </div>
     
      
    </div>
    <div style="border-top: 1px solid #EEEEEE; height: 45px;padding-top: 10px;" class="b_div lt-flex-between" >
      <div>
         <el-button style="margin-left: 148px;" @click="save()" >保存</el-button>
      </div>
      <div> </div>
      <div></div>
      
    </div>
  
    <div id="d2">
      <el-table :data="form.paymentDetailList" border show-summary style="width: 100%">
        <el-table-column type="index" :index="indexMethod">
        </el-table-column>
        <el-table-column prop="productName" label="产品名" width="180">
        </el-table-column>
        <el-table-column prop="specModel" label="规格型号">
        </el-table-column>
        <el-table-column prop="productUnit" sortable label="产品单位">
        </el-table-column>
        <el-table-column prop="paymentPrice" sortable label="产品单价">
        </el-table-column>
        <el-table-column prop="paymentQuantity" sortable label="数量">
        </el-table-column>
        <el-table-column prop="paymentSubtotal" sortable label="小计">
        </el-table-column>
      </el-table>

    </div>
  
  </div>
</el-form>
</template>
<script>

export default {
    data() {
      return {
        dialogVisible: false,
        pageParam: {
					"pageNum": 1,
					"pageSize": 10
				},
        form:{
        },
        purchase: {
					dialogVisible: false,
					searchInput: '',
					tableData: [],
					tableTotal: '',
					singleSelection: {},
					pageParam: {
						"pageNum": 1,
						"pageSize": 10
					}
				},
          dialogTableVisible: false,
          dialogFormVisible: false,
        
          formLabelWidth: '100px',
          employeeSelectValue:[],
          options: [{
            value: '现金'
          }, {
            value: '微信',
          }, {
            value: '支付宝',
          }],
          value: '',
          shi: '',
          tableData: [{
            id: '12987122',
            name: '王小虎',
            amount1: '234',
            amount2: '3.2',
            amount3: 10
          }, {
            id: '12987123',
            name: '王小虎',
            amount1: '165',
            amount2: '4.43',
            amount3: 12
          }, {
            id: '12987124',
            name: '王小虎',
            amount1: '324',
            amount2: '1.9',
            amount3: 9
          }, {
            id: '12987125',
            name: '王小虎',
            amount1: '621',
            amount2: '2.2',
            amount3: 17
          }, {
            id: '12987126',
            name: '王小虎',
            amount1: '539',
            amount2: '4.1',
            amount3: 15
          }]
      };
    
    },
    computed: {
			searchCondition() {
				return {
					purchReturnDocunum: this.purchase.searchInput,				
					employeeName : this.purchase.searchInput,
          supplierName: this.purchase.searchInput
				}
			}
		},
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      
      examine(){

        console.log(this.$route.params.gatherRefundId)
				this.axios({
					url:"http://localhost:8089/eims/gatherRefund/one",
					method:'get',
					params:{
            "id":this.$route.params.gatherRefundId
          }
         
				}).then((response) =>{
					this.form = response.data
          console.log(this.form)
				}).catch((error) =>{

				}) 
			},
      getDocuNum(prefix) {
				const nowDate = new Date()

				var month = nowDate.getMonth() + 1
				month = month > 10 ? month : '0' + month

				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date

				this.form.payDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"

				this.axios({
					url: 'http://localhost:8089/eims/gatherRefund/search',
					method: 'get',
					params: {
						'payDocunum': this.form.payDocunum
					}
				}).then(response => {
					console.log(response)
					const docuNumSequence = (Array(5).join(0) + (response.data.list.length + 1)).slice(-5)
					this.form.payDocunum = this.form.payDocunum + docuNumSequence
				}).catch(error => {

				})

			},
      clickEmployeeSelect(){
        if (this.employeeSelectValue.length > 0)
        return false

      this.axios({
        url: 'http://localhost:8089/eims/employee',
        method: 'get'
      }).then(response => {
        console.log(response.data.data)
        this.employeeSelectValue = response.data.list
      }).catch(error => {

      })
      },
      PaymentConfirmButton() {
				this.form.dialogFormVisible = false
        var payDocunum = this.form.payDocunum
				this.form  = this.purchase.singleSelection
        this.form.payDocunum = payDocunum

        console.log(this.form.purchReturnId)
        
        this.axios({
					url:"http://localhost:8089/eims/purchaseReturn/one",
					method:'get',
					params:{
            "id":this.form.purchReturnId
          }
         
				}).then((response) =>{

          console.log(response.data)
					this.form.paymentDetailList = response.data.purchaseDetailList
          
          var total = 0
          this.form.paymentDetailList.forEach(pay =>{
            pay.paymentPrice = pay.purchasePrice    
            pay.paymentQuantity = pay.purchaseQuantity
            pay.paymentSubtotal = pay.purchaseSubtotal
            total = total + pay.paymentSubtotal
            pay.payDocunum = this.form.payDocunum
          })
          this.form.paymentAmount  = total
         
				}).catch((error) =>{

				}) 
        
			},
      save(){
        if(this.isAdd){
          //判断当前页面加载进来的payId是否为空，否就执行新增方法，是就执行修改方法
          this.axios({
            url:"http://localhost:8089/eims/gatherRefund",
            method:'post',
            data:this.form
          }).then(response =>{
            this.$message({
              type: 'success',
              message: '付款单数据新增成功！'
            })

            this.$router.push({
              name: 'GatherRefund'
            })
          }).catch(error => {

          })
        }else{
          if(this.form.audited == 1){
            this.$message({
              type:"info",
              message:"已审核的付款单无法修改"
            })
          }else{
            this.axios({
              url:"http://localhost:8089/eims/gatherRefund",
              method:'put',
              data:this.form
            }).then(response =>{
              this.$message({
                type:'success',
                message:"付款单修改成功！"
              })
              this.$router.push({
                name:"GatherRefund"
              })
            })
          }
        }
        
      },
      
      loadData(){
        this.axios({
          url:"http://localhost:8089/eims/purchaseReturn",
          method:'get',
          params:this.pageParam
        }).then((response) =>{
          this.purchase.tableData = response.data.list
          this.purchase.tableTotal = response.data.total
          console.log(response)
        }).catch((error) =>{

        }) 
      },
      
      handleSearch() {
				this.queryType = 'search'

				console.log(this.searchInput)
				console.log(this.searchCondition)
				var searchForm = Object.assign(this.searchCondition,this.pageParam)
				
				this.axios({
					url: "http://localhost:8089/eims/purchaseReturn/search",
					method: "get",
					params: searchForm
				}).then(response => {
          console.log(response)
					this.purchase.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {

				})
			},
      handleSelectionChange(val) {
				console.log(val)
				this.multipleSelection = val

				
			},
      PurchaseSelectionChange(val){
        console.log("值是")
        console.log(val)
        this.purchase.singleSelection = val
      },
      handleSizeChange(val) {
				this.pageParam.pageSize=val
			
				
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val

			
				
			},
    },
    created() {

    console.log(this.$route.params.gatherRefundId)
      this.form.gatherRefundId = this.$route.params.gatherRefundId
      this.isAdd = typeof(this.form.gatherRefundId) == "undefined" || this.form.gatherRefundId == ""
      if (this.isAdd)
        this.getDocuNum("TKD")  
      else
        this.examine()
      
			
      
    }
    
  };
</script>

<!-- <style scoped>
  @import url('../css/fkd.css');
</style> -->
<style>
  /* #d{
    
   
} */
  /* #d>div{
    flex: 1 1 auto;
}
#d>div:nth-child(1){

    background-color: blueviolet;
}
#d>div:nth-child(2){
   background-color: saddlebrown;
}
#d>div:nth-child(3){
    background-color: seagreen;
}
#d>div:nth-child(3){
    background-color: seagreen;
} */

  #d1 {
    border: 0px solid red;

    
  }

  #d2 {
    border: 0px solid yellow;
    
    height: 100%;
    padding:10px;
  }



  .el-input-group--append .el-input__inner {
    height: 28px;

  }

  .xin .el-input__inner {
    height: 28px;
    margin-top: 4px;
    width: 190px
  }

  .xin1 .el-input__inner {
    height: 28px;

  }
</style>
<style scoped>
  @import url('../../assets/css/flex.css');
</style>