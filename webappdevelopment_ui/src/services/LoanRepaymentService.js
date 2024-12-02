import http from "../http-common"; 

class LoanRepaymentService {
  getAllLoanRepayments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/loanRepayment/loanRepayments`, searchDTO);
  }

  get(loanRepaymentId) {
    return this.getRequest(`/loanRepayment/${loanRepaymentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/loanRepayment?field=${matchData}`, null);
  }

  addLoanRepayment(data) {
    return http.post("/loanRepayment/addLoanRepayment", data);
  }

  update(data) {
  	return http.post("/loanRepayment/updateLoanRepayment", data);
  }
  
  uploadImage(data,loanRepaymentId) {
  	return http.postForm("/loanRepayment/uploadImage/"+loanRepaymentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new LoanRepaymentService();
