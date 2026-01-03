import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';
import './EmployeeView.css';

function EmployeeView() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [employee, setEmployee] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadEmployee();
  }, [id]);

  const loadEmployee = () => {
    EmployeeService.getEmployeeById(id)
      .then(response => {
        setEmployee(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching employee:', error);
        setError('Employee not found or backend is not running.');
        setLoading(false);
      });
  };

  const handleBack = () => {
    navigate('/');
  };

  const handleEdit = () => {
    navigate(`/edit/${id}`);
  };

  const handleDelete = () => {
    if (window.confirm('Are you sure you want to delete this employee?')) {
      EmployeeService.deleteEmployee(id)
        .then(() => {
          alert('Employee deleted successfully!');
          navigate('/');
        })
        .catch(error => {
          console.error('Error deleting employee:', error);
          alert('Failed to delete employee.');
        });
    }
  };

  if (loading) {
    return <div className="loading">Loading employee details...</div>;
  }

  if (error || !employee) {
    return (
      <div className="error-container">
        <h2>Error</h2>
        <p>{error}</p>
        <button onClick={handleBack}>Back to List</button>
      </div>
    );
  }

  return (
    <div className="employee-view-container">
      <div className="view-header">
        <h1>Employee Details</h1>
        <div className="header-actions">
          <button className="btn-edit" onClick={handleEdit}>
            Edit
          </button>
          <button className="btn-delete" onClick={handleDelete}>
            Delete
          </button>
          <button className="btn-back" onClick={handleBack}>
            Back to List
          </button>
        </div>
      </div>

      <div className="employee-card">
        <div className="detail-row">
          <span className="label">ID:</span>
          <span className="value">{employee.id}</span>
        </div>

        <div className="detail-row">
          <span className="label">Name:</span>
          <span className="value">{employee.name}</span>
        </div>

        <div className="detail-row">
          <span className="label">Email:</span>
          <span className="value">{employee.email}</span>
        </div>

        <div className="detail-row">
          <span className="label">Department:</span>
          <span className="value">{employee.department}</span>
        </div>

        <div className="detail-row">
          <span className="label">Salary:</span>
          <span className="value">
            ₹{employee.salary ? employee.salary.toLocaleString() : 'N/A'}
          </span>
        </div>
      </div>
    </div>
  );
}

export default EmployeeView;