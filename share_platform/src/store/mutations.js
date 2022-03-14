const mutations={
    commitSearchKey(state,key){
        state.searchKey=key
    },
    commitDetailGoodsId(state,key){
        state.detailGoodsId=key
    },
    commitEditEntrustId(state,key){
        state.editEntrustId=key
    },
    commitIsLogin(state,key){
        state.isLogin=key
    }
}

export default mutations;
