import React from 'react';
import ReactDOM from 'react-dom';
import './bootstrap-yeti.css'
// import './bootstrap.css'


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <>
                <Navbar />
                <Home />
            </>
        );
    }

}


/* NAV */

function Navbar(props) {
    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark px-5">
            <div className="container-fluid">
                <Logo href="#"/>
                <NavLinks />
                <Profile />
            </div>
        </nav>
    );
}

function Logo(props) {
    return (
        <a className="navbar-brand" href={props.href}>Coins</a>
    )
}

function NavLinks(props) {
    return (
        <div className="navbar-nav me-auto">
            <NavLink href="#" label="Nav Link 1" />
            <NavLink href="#" label="Nav Link 2" />
        </div>
    )
}

function NavLink(props) {
    return (
        <a className="nav-link" href={props.href}>{props.label}</a>
    )
}

function Profile(props) {
    return (
        <div></div>
    )
}


/* HOME */

function Home(props) {
    const account = {
        name: "My Checking Account"
    }

    const template = {
        date: '06/23/2021',
        desc: 'My transaction',
        deposit: null,
        payment: '4.20',
        balance: '1023.69'
    }

    const transactions = Array(20).fill(template);

    return (
        <div className="container-fluid">
            <div className="row justify-content-center">
                <div className="col-7">

                    <div className="container my-4">
                        <AccountInfo account={account} />
                        <NewTransactionForm />
                    </div>

                    <hr />

                    <div className="container my-4">
                        <h2 className="text-center my-4">Transactions</h2>

                        <table className="table table-responsive table-striped">
                            <TableHead />
                            <TableBody transactions={transactions}/>
                            
                        </table>
                    </div>

                </div>
            </div>
        </div>
    );
}

function AccountInfo(props) {
    return (
        <h3 className="text-center">Account: {props.account.name}</h3>
    );
}

function NewTransactionForm(props) {

    function handleSubmit(e) {
        console.log("Submitted form with event: " + e);
    }

    const dateParts = new Intl.DateTimeFormat("en-US", { year: "numeric", month: "2-digit", day: "2-digit" }).formatToParts(new Date());
    const dateString = dateParts[4].value + '-' + dateParts[0].value + '-' + dateParts[2].value;

    return (
        <div className="container my-4">
            <h4>New Transaction</h4>
            <form onSubmit={handleSubmit}>

                <label className="form-label">Description</label>
                <div className="input-group mb-3">
                    <div className="col-3">
                        <input type="date" className="form-control" id="formDate" defaultValue={dateString} />
                    </div>

                    <input type="text" className="form-control" id="formDescription" placeholder="Groceries" />
                </div>

                <label className="form-label">Amount</label>
                <div className="input-group mb-3">
                    <div className="col-3">
                        <select className="form-select" id="formTransactionType">
                            <option value="deposit" defaultValue>Deposit</option>
                            <option value="payment">Payment</option>
                        </select>
                    </div>

                    <span className="input-group-text">$</span>

                    <input type="text" className="form-control" id="formAmount" pattern="[0-9]+(.[0-9]{0,2})?" />

                    <button className="btn btn-primary" type="submit">Create Transaction</button>
                </div>

            </form>
        </div>
    );
}

function TableHead(props) {
    return (
        <thead>
            <tr>
                <th scope="col" className="text-center">Date</th>
                <th scope="col" className="text-center">Description</th>
                <th scope="col" className="text-center">Deposit</th>
                <th scope="col" className="text-center">Payment</th>
                <th scope="col" className="text-center">Balance</th>
            </tr>
        </thead>
    );
}

function TableBody(props) {

    let totalTransactions = props.transactions.length;
    let rows = Array(totalTransactions).fill(null);

    for (let i = 0; i < totalTransactions; i++) {

        let trans = props.transactions[i];
        rows[i] = (
            <tr key={i}>
                <td>{trans.date}</td>
                <td>{trans.desc}</td>
                <td className="text-end">{trans.deposit ? '$' + trans.deposit : null}</td>
                <td className="text-end">{trans.payment ? '$' + trans.payment : null}</td>
                <th className="text-end">${trans.balance}</th>
            </tr>
        )

    }

    return (
        <tbody>
            {rows}
        </tbody>
    );

}


// ==========================

ReactDOM.render(
    <App />,
    document.getElementById('root')
);
