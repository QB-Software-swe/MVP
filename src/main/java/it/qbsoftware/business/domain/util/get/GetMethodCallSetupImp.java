package it.qbsoftware.business.domain.util.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.repository.EntityRetriveRepository;
import it.qbsoftware.business.ports.in.jmap.GetMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public class GetMethodCallSetupImp<EntityType> implements GetMethodCallSetup<EntityType> {
    final ResultReferenceResolverPort resultReferenceResolverPort;
    final EntityRetriveRepository<EntityType> entityRetriveRepository;

    public GetMethodCallSetupImp(final ResultReferenceResolverPort resultReferenceResolverPort,
            final EntityRetriveRepository<EntityType> entityRetriveRepository) {
        this.resultReferenceResolverPort = resultReferenceResolverPort;
        this.entityRetriveRepository = entityRetriveRepository;
    }

    private String[] fectchEntityId(final GetMethodCallPort getMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption {
        final InvocationResultReferencePort invocationResultReferencePort = getMethodCallPort.getIdsReference();

        if (invocationResultReferencePort != null) {
            try {
                return resultReferenceResolverPort.resolve(getMethodCallPort.getIdsReference(), previousResponses);
            } catch (Exception exception) {
                throw new InvalidResultReferenceExecption();
            }
        }

        return getMethodCallPort.getIds();
    }

    @SuppressWarnings("unchecked")
    @Override
    public GetRetrivedEntity<EntityType> getEntity(final GetMethodCallPort getMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption {
        final String[] entityIds = fectchEntityId(getMethodCallPort, previousResponses);
        final Map<String, EntityType> entities = entityRetriveRepository.retrive(entityIds);

        final List<EntityType> found = new ArrayList<EntityType>();
        final List<String> notFound = new ArrayList<String>();
        for (Map.Entry<String, EntityType> entry : entities.entrySet()) {
            if (entry.getValue() == null) {
                notFound.add(entry.getKey());
            } else {
                found.add(entry.getValue());
            }
        }

        return new GetEntityRetrivedImp<EntityType>((EntityType[]) found.toArray(), notFound.toArray(String[]::new));
    }

}
