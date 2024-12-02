import http from "../http-common"; 

class PreliminaryOfferService {
  getAllPreliminaryOffers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/preliminaryOffer/preliminaryOffers`, searchDTO);
  }

  get(preliminaryOfferId) {
    return this.getRequest(`/preliminaryOffer/${preliminaryOfferId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/preliminaryOffer?field=${matchData}`, null);
  }

  addPreliminaryOffer(data) {
    return http.post("/preliminaryOffer/addPreliminaryOffer", data);
  }

  update(data) {
  	return http.post("/preliminaryOffer/updatePreliminaryOffer", data);
  }
  
  uploadImage(data,preliminaryOfferId) {
  	return http.postForm("/preliminaryOffer/uploadImage/"+preliminaryOfferId, data);
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

export default new PreliminaryOfferService();
