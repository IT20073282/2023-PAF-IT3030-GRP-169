import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService';

class CreateEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            emailId: '',
            errors: {
                firstName: '',
                lastName: '',
                emailId: ''
            }
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.saveOrUpdateEmployee = this.saveOrUpdateEmployee.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
                let employee = res.data;
                this.setState({firstName: employee.firstName,
                    lastName: employee.lastName,
                    emailId : employee.emailId
                });
            });
        }        
    }
    
    validateForm() {
        let errors = {};
        let formIsValid = true;

        if (!this.state.firstName) {
            formIsValid = false;
            errors.firstName = "Please enter your first name.";
        }

        if (!this.state.lastName) {
            formIsValid = false;
            errors.lastName = "Please enter your last name.";
        }

        if (!this.state.emailId) {
            formIsValid = false;
            errors.emailId = "Please enter your email address.";
        } else if (typeof this.state.emailId !== "undefined") {
            //regular expression for email validation
            let pattern = new RegExp(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/);
            if (!pattern.test(this.state.emailId)) {
                formIsValid = false;
                errors.emailId = "Please enter a valid email address.";
            }
        }

        this.setState({
            errors: errors
        });

        return formIsValid;
    }

    saveOrUpdateEmployee = (e) => {
        e.preventDefault();
        if (this.validateForm()) {
            let employee = {firstName: this.state.firstName, lastName: this.state.lastName, emailId: this.state.emailId};
            console.log('employee => ' + JSON.stringify(employee));

            // step 5
            if(this.state.id === '_add'){
                EmployeeService.createEmployee(employee).then(res =>{
                    this.props.history.push('/employees');
                });
            }else{
                EmployeeService.updateEmployee(employee, this.state.id).then( res => {
                    this.props.history.push('/employees');
                });
            }
        }
    }
    
    changeFirstNameHandler(event) {
        this.setState({
            firstName: event.target.value
        });
    }

    changeLastNameHandler(event) {
        this.setState({
            lastName: event.target.value
        });
    }

    changeEmailHandler(event) {
        this.setState({
            emailId: event.target.value
        });
    }

    cancel() {
        this.props.history.push('/employees');
    }

    getTitle() {
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Employee</h3>
        }else{
            return <h3 className="text-center">Update Employee</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {this.getTitle()}
                            <div className="card-body">
                                {this.state.errors.firstName && (
                                    <div className="alert alert-danger">
                                        {this.state.errors.firstName}
                                    </div>
                                )}
                                <form>
                                    <div className="form-group">
                                        <label> First Name: </label>
                                        <input
                                            placeholder="First Name"
                                            name="firstName"
                                            className="form-control"
                                            value={this.state.firstName}
                                            onChange={this.changeFirstNameHandler}
                                        />
                                    </div>
                                    {this.state.errors.lastName && (
                                        <div className="alert alert-danger">
                                            {this.state.errors.lastName}
                                        </div>
                                    )}
                                    <div className="form-group">
                                        <label> Last Name: </label>
                                        <input
                                            placeholder="Last Name"
                                            name="lastName"
                                            className="form-control"
                                            value={this.state.lastName}
                                            onChange={this.changeLastNameHandler}
                                        />
                                    </div>
                                    {this.state.errors.emailId && (
                                        <div className="alert alert-danger">
                                            {this.state.errors.emailId}
                                        </div>
                                    )}
                                    <div className="form-group">
                                        <label> Email Id: </label>
                                        <input
                                            placeholder="Email Address"
                                            name="emailId"
                                            className="form-control"
                                            value={this.state.emailId}
                                            onChange={this.changeEmailHandler}
                                        />
                                    </div>
                                    <button
                                        className="btn btn-success"
                                        onClick={this.saveOrUpdateEmployee}
                                    >
                                        Save
                                    </button>
                                    <button
                                        className="btn btn-danger"
                                        onClick={this.cancel.bind(this)}
                                        style={{ marginLeft: "10px" }}
                                    >
                                        Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
    
    
}

export default CreateEmployeeComponent
