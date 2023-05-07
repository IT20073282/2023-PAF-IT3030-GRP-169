import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            emailId: '',
            emailError: ''
        };

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
    }

    componentDidMount() {
        EmployeeService.getEmployeeById(this.state.id).then((res) => {
            let employee = res.data;
            this.setState({
                firstName: employee.firstName,
                lastName: employee.lastName,
                emailId: employee.emailId
            });
        });
    }

    handleEmailChange(event) {
        const email = event.target.value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const isValidEmail = emailRegex.test(email);
        this.setState({
            emailId: email,
            emailError: isValidEmail ? '' : 'Invalid email format'
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        // submit form logic
    }

    updateEmployee(e) {
        e.preventDefault();
        let employee = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            emailId: this.state.emailId
        };
        console.log('employee => ' + JSON.stringify(employee));
        console.log('id => ' + JSON.stringify(this.state.id));
        EmployeeService.updateEmployee(employee, this.state.id).then((res) => {
            this.props.history.push('/employees');
        });
    }

    changeFirstNameHandler(event) {
        this.setState({ firstName: event.target.value });
    }

    changeLastNameHandler(event) {
        this.setState({ lastName: event.target.value });
    }

    changeEmailHandler(event) {
        this.setState({ emailId: event.target.value });
    }

    cancel() {
        this.props.history.push('/employees');
    }
    render() {
        return (
          <div>
            <br />
            <div className="container">
              <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                  <h3 className="text-center">Update Employee</h3>
                  <div className="card-body">
                    <form onSubmit={this.handleSubmit}>
                      <div className="form-group">
                        <label> First Name: </label>
                        <input placeholder="First Name" name="firstName" className="form-control"
                          value={this.state.firstName} onChange={this.changeFirstNameHandler} />
                      </div>
                      <div className="form-group">
                        <label> Last Name: </label>
                        <input placeholder="Last Name" name="lastName" className="form-control"
                          value={this.state.lastName} onChange={this.changeLastNameHandler} />
                      </div>
                      <div className="form-group">
                        <label>Email</label>
                        <input type="email" className="form-control" placeholder="Enter email"
                          value={this.state.email} onChange={this.handleEmailChange} />
                        {this.state.emailError && <div className="alert alert-danger">{this.state.emailError}</div>}
                      </div>
      
                      <button className="btn btn-success" onClick={this.updateEmployee}>Save</button>
                      <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
                    </form>
                  </div>
                </div>
              </div>
      
            </div>
          </div>
        )
      }
      
}

export default UpdateEmployeeComponent
