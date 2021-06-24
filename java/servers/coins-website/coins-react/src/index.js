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
                <div class="container-fluid p-0">
                    <Navbar />
                    <Home />
                </div>
            </>
        );
    }

}


/* NAV */

function Navbar(props) {
    return (
        <nav class="navbar navbar-expand navbar-light bg-light px-5">
            <Logo />
            <NavLinks />
            <Profile />
        </nav>
    );
}

function Logo(props) {
    return (
        <a class="navbar-brand" href={props.href}>Coins</a>
    )
}

function NavLinks(props) {
    return (
        <div class="collapse navbar-collapse" id="coinsNav">
            <div class="navbar-nav">
                <NavLink href="/link1" label="Nav Link 1" />
                <NavLink href="/link2" label="Nav Link 2" />
            </div>
        </div>
    )
}

function NavLink(props) {
    return (
        <a class="nav-link" href={props.href}>{props.label}</a>
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
        <div class="row justify-content-center">
            <div class="col-6">
                <h2 class="text-center my-4">Transactions</h2>
                <table class="table table-responsive table-striped">
                    <TableHead />
                    <TableBody transactions={transactions}/>
                </table>
            </div>
        </div>
    );
}

function TableHead(props) {
    return (
        <thead>
            <tr>
                <th scope="col" class="text-center">Date</th>
                <th scope="col" class="text-center">Description</th>
                <th scope="col" class="text-center">Deposit</th>
                <th scope="col" class="text-center">Payment</th>
                <th scope="col" class="text-center">Balance</th>
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
            <tr>
                <td>{trans.date}</td>
                <td>{trans.desc}</td>
                <td class="text-end">{trans.deposit ? '$' + trans.deposit : null}</td>
                <td class="text-end">{trans.payment ? '$' + trans.payment : null}</td>
                <th scope="row" class="text-end">${trans.balance}</th>
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
