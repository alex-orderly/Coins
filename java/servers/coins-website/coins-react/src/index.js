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
    return (
        <div class="row justify-content-center">
            <div class="col-8">
                <h2 class="text-center my-4">Transactions</h2>
                <table class="table table-responsive table-striped">
                    <TableHead />
                    <TableBody />
                </table>
            </div>
        </div>
    );
}

function TableHead(props) {
    return (
        <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Description</th>
                <th scope="col">Deposit</th>
                <th scope="col">Payment</th>
                <th scope="col">Balance</th>
            </tr>
        </thead>
    );
}

function TableBody(props) {
    let rows = Array(10).fill(null);
    for (let i = 0; i < 10; i++) {
        rows[i] = (
            <tr>
                <td>06/21/2021 {i}</td>
                <td>My transaction</td>
                <td></td>
                <td>$100.00</td>
                <td>$1,023.00</td>
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
