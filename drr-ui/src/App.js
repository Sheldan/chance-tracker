import './App.css';
import {ApiKeyInput} from "./components/ApiKeyInput";
import {ItemDifference} from "./components/ItemDifference";
import {LockStateInput} from "./components/LockStateInput";


function App() {
  return (
      <div className="App">
        <ApiKeyInput />
        <LockStateInput />
        <ItemDifference />
      </div>
  );
}

export default App;
