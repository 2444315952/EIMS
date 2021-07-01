import axios from "axios";

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
					purchDocunum: this.purchase.searchInput,				
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
				this.axios({
					url:"http://localhost:8089/eims/payment/one",
					method:'get',
					params:{
            "id":this.$route.params.payId
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
					url: 'http://localhost:8089/eims/payment/search',
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
        this.axios({
					url:"http://localhost:8089/eims/purchase/one",
					method:'get',
					params:{
            "id":this.form.purchId
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
            url:"http://localhost:8089/eims/payment",
            method:'post',
            data:this.form
          }).then(response =>{
            this.$message({
              type: 'success',
              message: '付款单数据新增成功！'
            })

            this.$router.push({
              name: 'PaymentList'
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
              url:"http://localhost:8089/eims/payment",
              method:'put',
              data:this.form
            }).then(response =>{
              this.$message({
                type:'success',
                message:"付款单修改成功！"
              })
              this.$router.push({
                name:"PaymentList"
              })
            })
          }
        }
        
      },
      
      loadData(){
        this.axios({
          url:"http://localhost:8089/eims/purchase",
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
					url: "http://localhost:8089/eims/purchase/search",
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

      this.form.payId = this.$route.params.payId
      this.isAdd = typeof(this.form.payId) == "undefined" || this.form.payId == ""
      if (this.isAdd)
        this.getDocuNum("FKD")  
      else
        this.examine()
      
			
      
    }
    
  };