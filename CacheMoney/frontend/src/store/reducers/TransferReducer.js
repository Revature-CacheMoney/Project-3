/**
 * @author Cody Gonsowski
 */

const initialState = {
    sourceAccountId: -1,
    destinationAccountId: -1
}

function transferReducer(state = initialState, action) {
    switch (action.type) {
        case "UPDATE_SOURCE_ACCOUNT_ID":
            return {
                ...state,
                sourceAccountId: action.payload
            };

        case "UPDATE_DESTINATION_ACCOUNT_ID":
            return {
                ...state,
                destinationAccountId: action.payload
            };

        default:
            return state;
    }
}

export default transferReducer;
