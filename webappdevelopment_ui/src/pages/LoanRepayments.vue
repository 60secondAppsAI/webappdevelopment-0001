<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <loanRepayment-table
            v-if="loanRepayments && loanRepayments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:loanRepayments="loanRepayments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-loan-repayments="getAllLoanRepayments"
             >

            </loanRepayment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LoanRepaymentTable from "@/components/LoanRepaymentTable";
import LoanRepaymentService from "../services/LoanRepaymentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LoanRepaymentTable,
  },
  data() {
    return {
      loanRepayments: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllLoanRepayments(sortBy='loanRepaymentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LoanRepaymentService.getAllLoanRepayments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.loanRepayments.length) {
					this.loanRepayments = response.data.loanRepayments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching loanRepayments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching loanRepayment details:", error);
      }
    },
  },
  mounted() {
    this.getAllLoanRepayments();
  },
  created() {
    this.$root.$on('searchQueryForLoanRepaymentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLoanRepayments();
    })
  }
};
</script>
<style></style>
