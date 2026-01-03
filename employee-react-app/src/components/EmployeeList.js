import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';
import './EmployeeList.css';

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = () => {
    setLoading(true);
    EmployeeService.getAllEmployees()
      .then(response => {
        setEmployees(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching employees:', error);
        setError('Failed to load employees. Make sure backend is running on port 8080.');
        setLoading(false);
      });
  };

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this employee?')) {
      EmployeeService.deleteEmployee(id)
        .then(() => {
          loadEmployees();
          alert('Employee deleted successfully!');
        })
        .catch(error => {
          console.error('Error deleting employee:', error);
          alert('Failed to delete employee.');
        });
    }
  };

  const handleView = (id) => {
    navigate(`/view/${id}`);
  };

  const handleEdit = (id) => {
    navigate(`/edit/${id}`);
  };

  const handleAdd = () => {
    navigate('/add');
  };

  if (loading) {
    return <div className="loading">Loading employees...</div>;
  }

  if (error) {
    return (
      <div className="error-container">
        <h2>Error</h2>
        <p>{error}</p>
        <button onClick={loadEmployees}>Retry</button>
      </div>
    );
  }

  return (
    <div className="employee-list-container">
      <div className="header">
        <h1>Employee Management System</h1>
        <button className="btn-add" onClick={handleAdd}>
          + Add New Employee
        </button>
      </div>

      <table className="employee-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.length === 0 ? (
            <tr>
              <td colSpan="6" style={{ textAlign: 'center' }}>
                No employees found. Add your first employee!
              </td>
            </tr>
          ) : (
            employees.map(employee => (
              <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.name}</td>
                <td>{employee.email}</td>
                <td>{employee.department}</td>
                <td>₹{employee.salary ? employee.salary.toLocaleString() : 'N/A'}</td>
                <td className="actions">
                  <button className="btn-view" onClick={() => handleView(employee.id)}>
                    View
                  </button>
                  <button className="btn-edit" onClick={() => handleEdit(employee.id)}>
                    Edit
                  </button>
                  <button className="btn-delete" onClick={() => handleDelete(employee.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default EmployeeList;