import React, {Component} from 'react';
import 'bulma/css/bulma.css';
import ReactDice from 'react-dice-complete'
import 'react-dice-complete/dist/react-dice-complete.css'
import { confirmAlert } from 'react-confirm-alert'; // Import
import '../css/react-confirm-alert.css' // Import css

class App extends Component {

    submit = (num) => {
        confirmAlert({
            title: 'You rolled a...',                        // Title dialog
            message: num,               // Message dialog
            confirmLabel: 'OK',
            onConfirm: () => this.setState({
                diceRolling: false
            }),    // Action after Confirm
        })
    };

    componentWillMount() {
        this.setState({
            diceRolling: false
        });
    }


    rollAll = () => {
        if (!this.state.diceRolling) {
            this.setState({
                diceRolling: true
            });
            this.reactDice.rollAll()
        }
    };

    rollDoneCallback = (num) => {
        if (this.state.diceRolling) {
            this.submit(num);

        }
    };




    render() {
        return (
           <div>
               <section className="hero is-primary">
                   <div className="hero-body">
                       <div className="container">
                           <h1 className="title">
                               Welcome to Lesson 1!
                           </h1>
                           <h2 className="subtitle">
                               Let's roll some dice!
                           </h2>
                       </div>
                   </div>
               </section>
               {/*<section className="section is-small"/>*/}
                <div className="verticalSpace">
                   <div className="field has-addons has-addons-centered">
                       <ReactDice
                           numDice={1}
                           dieSize={70}
                           rollDone={this.rollDoneCallback}
                           ref={dice => this.reactDice = dice}
                       />
                   </div>
                   <div className="field has-addons has-addons-centered">
                       <button onClick={this.rollAll} disabled={this.state.diceRolling} className="button is-large is-info">Roll me</button>
                   </div>
                </div>
           </div>
        );
    }
}

export default App;