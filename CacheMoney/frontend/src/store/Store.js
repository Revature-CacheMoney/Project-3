/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import { combineReducers, createStore } from 'redux';
import userReducer from './reducers/UserReducer';
import accountReducer from './reducers/AccountReducer';


// const userStore = createStore(userReducer);
// const accountStore = createStore(accountReducer);
const store = createStore(combineReducers({ userReducer, accountReducer }));

export default store;
