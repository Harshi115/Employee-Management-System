import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';
import './EmployeeForm.css';

function EmployeeForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEditMode = Boolean(id);

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    department: '',
    salary: ''
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (isEditMode) {
      loadEmployee();
    }
  }, [id]);

  const loadEmployee = () => {
    setLoading(true);
    EmployeeService.getEmployeeById(id)
      .then(response => {
        const employee = response.data;
        setFormData({
          name: employee.name,
          email: employee.email,
          department: employee.department,
          salary: employee.salary || ''
        });
        setLoading(false);
      })
      .catch(error => {
        console.error('Error loading employee:', error);
        alert('Failed to load employee details.');
        navigate('/');
      });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
    // Clear error for this field
    if (errors[name]) {
      setErrors({
        ...errors,
        [name]: ''
      });
    }
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.name.trim()) {
      newErrors.name = 'Name is required';
    }

    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = 'Email is invalid';
    }

    if (!formData.department.trim()) {
      newErrors.department = 'Department is required';
    }

    if (!formData.salary) {
      newErrors.salary = 'Salary is required';
    } else if (isNaN(formData.salary) || Number(formData.salary) <= 0) {
      newErrors.salary = 'Salary must be a positive number';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setLoading(true);

    const employeeData = {
      ...formData,
      salary: Number(formData.salary)
    };

    const savePromise = isEditMode
      ? EmployeeService.updateEmployee(id, employeeData)
      : EmployeeService.createEmployee(employeeData);

    savePromise
      .then(() => {
        alert(isEditMode ? 'Employee updated successfully!' : 'Employee added successfully!');
        navigate('/');
      })
      .catch(error => {
        console.error('Error saving employee:', error);
        alert('Failed to save employee. Please check if backend is running.');
        setLoading(false);
      });
  };

  const handleCancel = () => {
    navigate('/');
  };

  if (loading && isEditMode) {
    return <div className="loading">Loading employee details...</div>;
  }

  return (
    <div className="employee-form-container">
      <h1>{isEditMode ? 'Edit Employee' : 'Add New Employee'}</h1>

      <form onSubmit={handleSubmit} className="employee-form">
        <div className="form-group">
          <label htmlFor="name">
            Name <span className="required">*</span>
          </label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            className={errors.name ? 'error' : ''}
            placeholder="Enter employee name"
          />
          {errors.name && <span className="error-message">{errors.name}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="email">
            Email <span className="required">*</span>
          </label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className={errors.email ? 'error' : ''}
            placeholder="Enter email address"
          />
          {errors.email && <span className="error-message">{errors.email}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="department">
            Department <span className="required">*</span>
          </label>
          <select
            id="department"
            name="department"
            value={formData.department}
            onChange={handleChange}
            className={errors.department ? 'error' : ''}
          >
            <option value="">Select Department</option>
            <option value="IT">IT</option>
            <option value="HR">HR</option>
            <option value="Finance">Finance</option>
            <option value="Marketing">Marketing</option>
            <option value="Sales">Sales</option>
          </select>
          {errors.department && <span className="error-message">{errors.department}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="salary">
            Salary <span className="required">*</span>
          </label>
          <input
            type="number"
            id="salary"
            name="salary"
            value={formData.salary}
            onChange={handleChange}
            className={errors.salary ? 'error' : ''}
            placeholder="Enter salary amount"
          />
          {errors.salary && <span className="error-message">{errors.salary}</span>}
        </div>

        <div className="form-actions">
          <button type="submit" className="btn-save" disabled={loading}>
            {loading ? 'Saving...' : (isEditMode ? 'Update Employee' : 'Save Employee')}
          </button>
          <button type="button" className="btn-cancel" onClick={handleCancel} disabled={loading}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default EmployeeForm;