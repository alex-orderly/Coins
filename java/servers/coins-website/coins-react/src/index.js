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
    const template = {
        date: '06/23/2021',
        desc: 'My transaction',
        deposit: null,
        payment: '40.20',
        balance: '1023.69'
    }

    const transactions = Array(10).fill(template);

    return (
        <div className="container-fluid">
            <div className="row justify-content-center">
                <div className="col-6">
                    <AccountInfo />

                    <hr />

                    <h2 className="text-center my-4">Transactions</h2>

                    <table className="table table-responsive table-striped">
                        <TableHead />
                        <TableBody transactions={transactions}/>
                    </table>
                </div>
            </div>
        </div>
    );
}

function AccountInfo(props) {
    return (
        <div>
            
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
