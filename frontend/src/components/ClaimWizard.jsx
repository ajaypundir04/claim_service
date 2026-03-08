import React, { useState, useEffect } from 'react';
import { funnelService } from '../services/funnelService';
import FunnelStep from '../components/FunnelStep';
import SuccessScreen from '../components/SuccessScreen';

const ClaimWizard = ({ funnelSlug = 'default-claim-funnel' }) => {
  const [config, setConfig] = useState(null);
  const [stepIdx, setStepIdx] = useState(0);
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({});
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    Promise.all([
      funnelService.getFunnelConfig(funnelSlug),
      funnelService.getUsers()
    ]).then(([configData, userData]) => {
      setConfig(configData);
      setUsers(userData);
    });
  }, [funnelSlug]);

  const handleSubmit = async () => {
    setLoading(true);
    try {
      const data = await funnelService.postClaim(formData);
      setResult(data);
      setStepIdx(-1); // Signal success
    } catch (e) {
      alert("Error submitting claim");
    } finally {
      setLoading(false);
    }
  };

  if (!config) return <div className="p-10 text-center">Loading...</div>;

  const steps = config.config.steps;
  const isSuccess = stepIdx === -1;

  return (
    <div className="max-w-md mx-auto bg-white shadow-xl rounded-xl overflow-hidden border">
      <div className="bg-blue-700 p-5 text-white">
        <h2 className="font-bold">{config.name}</h2>
      </div>

      <div className="p-6">
        {isSuccess ? (
          <SuccessScreen result={result} onReset={() => setStepIdx(0)} />
        ) : (
          <>
            <FunnelStep
              stepConfig={steps[stepIdx]}
              users={users}
              formData={formData}
              onChange={(e) => setFormData({...formData, [e.target.name]: e.target.value})}
            />

            <div className="flex gap-3 mt-8">
              {stepIdx > 0 && (
                <button onClick={() => setStepIdx(stepIdx - 1)} className="flex-1 p-3 bg-gray-100 rounded-lg font-bold">Back</button>
              )}
              <button
                onClick={() => stepIdx === steps.length - 1 ? handleSubmit() : setStepIdx(stepIdx + 1)}
                className="flex-[2] p-3 bg-blue-600 text-white rounded-lg font-bold"
              >
                {loading ? '...' : stepIdx === steps.length - 1 ? 'Submit' : 'Continue'}
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default ClaimWizard;