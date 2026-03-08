import React from 'react';

const FunnelStep = ({ stepConfig, users, formData, onChange }) => {
  const { type, name, label, placeholder, description } = stepConfig;

  return (
    <div className="animate-in fade-in slide-in-from-right duration-300">
      <label className="block text-sm font-semibold text-gray-600 mb-2">{label}</label>

      {type === 'select' && (
        <select
          name={name}
          className="w-full border-2 border-gray-100 p-3 rounded-lg focus:border-blue-500 outline-none"
          value={formData[name] || ''}
          onChange={onChange}
        >
          <option value="">-- Select Member --</option>
          {users.map(u => (
            <option key={u.id} value={u.id}>{u.username} ({u.name})</option>
          ))}
        </select>
      )}

      {type === 'text' && (
        <input
          name={name}
          type="text"
          placeholder={placeholder}
          className="w-full border-2 border-gray-100 p-3 rounded-lg focus:border-blue-500 outline-none"
          value={formData[name] || ''}
          onChange={onChange}
        />
      )}

      {type === 'textarea' && (
        <textarea
          name={name}
          placeholder={placeholder}
          className="w-full border-2 border-gray-100 p-3 rounded-lg focus:border-blue-500 outline-none h-32 resize-none"
          value={formData[name] || ''}
          onChange={onChange}
        />
      )}

      {description && <p className="text-xs text-gray-400 mt-2">{description}</p>}
    </div>
  );
};

export default FunnelStep;