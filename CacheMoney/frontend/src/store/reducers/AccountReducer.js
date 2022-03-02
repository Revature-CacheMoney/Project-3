/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

const initialState = {
    currentAccountId: ""
}

function accountReducer(state = initialState, action) {
    switch (action.type) {
        case "UPDATE_CURRENT_ACCOUNT_ID":
            return {
                ...state,
                currentAccountId: action.payload
            };

        default:
            return state;
    }
}

export default accountReducer;
