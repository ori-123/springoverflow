import React from "react";
import {Link, Outlet} from "react-router-dom";

//nav could be exported to refactor
function Layout() {

  return (
    <div className="Layout">
      <header>
        <nav>
          <Link to="/">
            <button>Questions</button>
          </Link>
          <Link to="/register">
            <button>Register</button>
          </Link>
          <Link to="/login">
            <button>Login</button>
          </Link>
        </nav>
      </header>
      <main>
        <Outlet className="Outlet"/>
      </main>
    </div>
  );
}

export default Layout;