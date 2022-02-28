import { createStore } from 'redux';
import reducer from './reducers/UserReducer';

const userStore = createStore(reducer);

export default userStore;
