const initialState = {
    userId: ""
}

function reducer(state = initialState, action) {
    switch (action.type) {
        case "UPDATE_ID":
            return {
                ...state,
                userId: action.payload
            };

        default:
            return state;
    }
}

export default reducer;
