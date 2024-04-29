package it.qbsoftware.business.domain.methodcall.process.set.update;

import java.util.HashMap;
import java.util.Map;

import it.qbsoftware.business.domain.exception.set.SetInvalidPatchException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class StandardUpdateEmail implements UpdateEmail {
    private final EmailRepository emailRepository;
    private final SetErrorEnumPort setErrorEnumPort;

    public StandardUpdateEmail(final EmailRepository emailRepository, final SetErrorEnumPort setErrorEnumPort) {
        this.emailRepository = emailRepository;
        this.setErrorEnumPort = setErrorEnumPort;
    }

    @Override
    public UpdatedResult<EmailPort> update(final SetEmailMethodCallPort setEmailMethodCall) {
        final String accountId = setEmailMethodCall.accountId();
        final HashMap<String, EmailPort> updated = new HashMap<>();
        final HashMap<String, SetErrorPort> notUpdated = new HashMap<>();

        final var mapEmailAndPatch = setEmailMethodCall.getUpdate();

        if (mapEmailAndPatch != null) {
            for (final Map.Entry<String, Map<String, Object>> mapEmailIdPatchObject : mapEmailAndPatch.entrySet()) {
                try {
                    final EmailPort emailSideEffectDiff = applayPatches(accountId, mapEmailIdPatchObject.getKey(),
                            mapEmailIdPatchObject.getValue());

                    updated.put(mapEmailIdPatchObject.getKey(), emailSideEffectDiff);

                } catch (final SetNotFoundException setNotFoundException) {
                    notUpdated.put(mapEmailIdPatchObject.getKey(), setErrorEnumPort.notFound());
                } catch (final SetInvalidPatchException setInvalidPatchException) {
                    notUpdated.put(mapEmailIdPatchObject.getKey(), setErrorEnumPort.invalidPatch());
                }
            }
        }

        return new UpdatedResult<EmailPort>(updated, notUpdated);
    }

    private EmailPort applayPatches(final String accountId, final String emailIdToPatch,
            final Map<String, Object> patchObjects)
            throws SetNotFoundException, SetInvalidPatchException {
        final EmailPort emailTarget = emailRepository.retriveOne(emailIdToPatch);
        final EmailBuilderPort emailToPatchBuilder = emailTarget.toBuilder();
        final EmailBuilderPort emailSideEffectDiffBuilder = emailTarget.toBuilder();

        for (final Map.Entry<String, Object> patchObject : patchObjects.entrySet()) {
            final String path = patchObject.getKey();
            final Object value = patchObject.getValue();

            switch (path) {
                // TODO: aggiungere le parti man mano che le richiede il client

                default:
                    throw new SetInvalidPatchException();
            }
        }

        emailRepository.overwrite(emailToPatchBuilder.build());
        return emailSideEffectDiffBuilder.build();
    }
}
