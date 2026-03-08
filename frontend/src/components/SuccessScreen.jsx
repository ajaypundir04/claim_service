import React from 'react';

const SuccessScreen = ({ result, onReset }) => {
  if (!result) return null;

  return (
    <div className="text-center space-y-4 animate-in zoom-in duration-500">
      {/* Success Icon */}
      <div className="w-16 h-16 bg-green-100 text-green-600 rounded-full flex items-center justify-center mx-auto text-3xl shadow-sm">
        ✓
      </div>

      <h3 className="text-xl font-bold text-gray-800">Claim Registered!</h3>

      {/* Claim Number Card */}
      <div className="bg-blue-50 p-5 rounded-lg border-2 border-blue-100 shadow-inner">
        <span className="text-xs text-blue-400 font-bold block uppercase tracking-widest mb-1">
          Reference Number
        </span>
        <span className="text-2xl font-mono font-black text-blue-800 tracking-tighter">
          {result.claimNumber}
        </span>
      </div>

      {/* Details Summary */}
      <div className="text-sm text-gray-500 space-y-1">
        <p>
          Claim created for <span className="font-semibold text-gray-700">{result.username}</span>.
        </p>
        <p className="italic">"{result.title}"</p>
      </div>

      <p className="text-xs text-gray-400 mt-4">
        We have received your request and a representative will review it shortly.
      </p>

      {/* Action Button */}
      <div className="pt-4">
        <button
          onClick={onReset}
          className="text-blue-600 text-sm font-bold hover:text-blue-800 hover:underline transition-colors"
        >
          File another claim
        </button>
      </div>
    </div>
  );
};

export default SuccessScreen;