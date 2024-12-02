import http from "../http-common"; 

class CustomerSubmissionService {
  getAllCustomerSubmissions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/customerSubmission/customerSubmissions`, searchDTO);
  }

  get(customerSubmissionId) {
    return this.getRequest(`/customerSubmission/${customerSubmissionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/customerSubmission?field=${matchData}`, null);
  }

  addCustomerSubmission(data) {
    return http.post("/customerSubmission/addCustomerSubmission", data);
  }

  update(data) {
  	return http.post("/customerSubmission/updateCustomerSubmission", data);
  }
  
  uploadImage(data,customerSubmissionId) {
  	return http.postForm("/customerSubmission/uploadImage/"+customerSubmissionId, data);
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

export default new CustomerSubmissionService();
