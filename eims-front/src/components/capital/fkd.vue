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
        采购单据编号：
        <el-input placeholder="(空)" disabled v-model="form.purchDocunum " class="input-with-select" style="width: 190px;margin-top: 4px;">
          <template #append>
            <el-button icon="el-icon-more" @click="form.dialogFormVisible = true;loadData()"></el-button>
          </template>
        </el-input>
        <el-dialog title="选择采购单" v-model="form.dialogFormVisible">
          <el-form :model="form">

            <div class=" lt-flex-left">
              <div class=" w83">

              </div>
              <div class=" w300">
                <el-form-item style="width: 300px;">
                  <el-input v-model="purchase.searchInput"  placeholder="采购单号/供应商名称/业务员名">
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
            <el-table-column prop="purchDocunum" label="采购单编号" width="170"></el-table-column>
            <el-table-column prop="supplierName" label="供应商名"></el-table-column>
            <el-table-column property="employeeName" label="业务员名" width="150"></el-table-column>
            <el-table-column property="warehouseName" label="仓库名" width="200"></el-table-column>
            <el-table-column property="audited" label="是否审核"></el-table-column>
            <el-table-column property="returnState" label="退货状态"></el-table-column>
            <el-table-column property="transactionAmount" label="交易金额"></el-table-column>
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
<script src="../../assets/js/fkd.js"></script>

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