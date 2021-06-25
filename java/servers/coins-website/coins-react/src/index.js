import React from 'react';
import ReactDOM from 'react-dom';
import './bootstrap-yeti.css'


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
        <nav className="navbar navbar-expand navbar-light bg-light px-5">
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
        payment: '40.20',
        balance: '1023.69'
    }

    const transactions = Array(20).fill(template);

    return (
        <div className="container-fluid">
            <div className="row justify-content-center">
                <div className="col-6">

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
    const dateParts = new Intl.DateTimeFormat("en-US", { year: "numeric", month: "2-digit", day: "2-digit" }).formatToParts(new Date());
    const dateString = dateParts[4].value + '-' + dateParts[0].value + '-' + dateParts[2].value;
    return (
        <div className="container my-4">
            <h4>New Transaction</h4>
            <form>
                <div class="mb-3">
                    <label for="formDate" class="form-label">Date</label>
                    <input type="date" class="form-control" id="formDate" value={dateString} />
                </div>
                <div class="mb-3">
                    <label for="formDescription" class="form-label">Description</label>
                    <input class="form-control" id="formDescription" />
                </div>
                <div class="mb-3">
                    <label for="formAmount" class="form-label">Amount</label>
                    <input class="form-control" id="formAmount" pattern="[0-9]+(.[0-9]{0,2})?" />
                </div>
                <div class="mb-3">
                    <button class="btn btn-primary" type="submit">Submit form</button>
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
