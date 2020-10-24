import React from 'react';
import logo from './logo.svg';
import './App.css';

function UseOfProps(props) {
   return (
     <div id="divUseOfProps">
       <h2>{props.title}</h2>
       <h4>{props.text}</h4>
     </div>
  )
}

class ClassComponent extends React.Component {
  state = {
    show : true
  }
  render() {
    if (this.state.show) {
    return (
 <div id="divUseOfProps">
       <h2>{this.props.title}</h2>
       <h4>{this.props.text}</h4>
       <button onClick={() => alert("Welcome")}>Show</button>
     </div>
    )
    } else {
      return (<h1>Not configured show attribute on true</h1>)
    }
  }
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> 
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        */}
        <UseOfProps title="This title is for the function component" text="this text is for the function component"/>
        <ClassComponent title="This title is for the Class component" text="this text is for the Class component"/>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
